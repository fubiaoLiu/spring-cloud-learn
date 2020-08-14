# Spring事务的传播机制

- PROPAGATION_REQUIRED`(默认)`：支持当前事务，如果当前没有事务，就新建一个事务。

- PROPAGATION_SUPPORTS：支持当前事务，如果当前没有事务，就以非事务方式执行。

- PROPAGATION_MANDATORY：支持当前事务，如果当前没有事务，就抛出异常。

- PROPAGATION_REQUIRES_NEW：新建事务，如果当前存在事务，把当前事务挂起。两个事务没有关系，外层事务回滚后，不能回滚内层事务；内层事务抛出异常，外层事务捕获，可以选择是否回滚。

- PROPAGATION_NOT_SUPPORTED：以非事务方式执行，如果当前存在事务，把当前事务挂起。

- PROPAGATION_NEVER：以非事务方式执行，如果当前存在事务，就抛出异常。

- PROPAGATION_NESTED：如果当前存在事务，则在嵌套事务中执行，如果当前没有事务则与PROPAGATION_REQUIRED一样。仅适用于特定的事务管理器，比如在JDBC 3.0时DataSourceTransactionManager才适用，一些JTA提供程序可能也支持嵌套事务。

# 接着`transaction-rollbackFor.md`中的实验
看看当A、B两个方法都定义了事务时的执行机制，只测试rollbackFor为Exception且方法B设置为PROPAGATION_REQUIRES_NEW`(两个独立事务)`和PROPAGATION_NESTED`(嵌套事务)`的情况。

#### ①、不捕获异常
```text
@Transactional(rollbackFor = Exception.class)
public void methodA() {
    // ① do something ...
    methodB();
    // ② do something ...
}
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
// @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
public void methodB() {}
```
- PROPAGATION_REQUIRES_NEW：
  - methodB()异常：B先回滚、A再回滚。
  - methodA()②处异常：B先回滚、A再回滚。
  - 正常执行：B先提交、A再提交。
- PROPAGATION_NESTED：
  - methodB()异常：A、B一起回滚。
  - methodA()②处异常：A、B一起回滚。
  - 正常执行：A、B一起提交。

#### ②、捕获异常但不处理
```text
@Transactional(rollbackFor = Exception.class)
public void methodA() {
    // ① do something ...
    try {
        methodB();
    } catch (Exception e) {
        // do nothing
    }
    // ② do something ...
}
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
// @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
public void methodB() {}
```
- PROPAGATION_REQUIRES_NEW：
  - methodB()异常：B先回滚，A再提交。
  - methodA()②处异常：A、B都回滚。
  - 正常执行：A、B都提交。
- PROPAGATION_NESTED：
  - methodB()异常：B先回滚，A再提交。
  - methodA()②处异常：A、B一起回滚。
  - 正常执行：A、B一起提交。

#### ③、捕获异常并抛出异常
```text
@Transactional(rollbackFor = Exception.class)
public void methodA() {
    // ① do something ...
    try {
        methodB();
    } catch (Exception e) {
        throw e;
        // 或
        // throw new RuntimeException();
        // 或
        // throw new Error();
        // 或
        // throw new Exception();
    }
    // ② do something ...
}
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
// @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
public void methodB() {}
```
- PROPAGATION_REQUIRES_NEW：
  - methodB()异常：B先回滚，A再回滚。
    **`(如果抛出的异常不是methodA的回滚异常，A不会回滚，①的操作提交、②不执行;同理，如果不是methodB的异常，B不会回滚)`**
  - methodA()②处异常：A、B都回滚。
  - 正常执行：B先提交、A再提交。
- PROPAGATION_NESTED：
  - methodB()异常：A、B一起回滚。
    **`(如果抛出的异常不是methodA的回滚异常，A不会回滚，①的操作提交、②不执行;同理，如果不是methodB的异常，B不会回滚)`**
  - methodA()②处异常：A、B一起回滚。
  - 正常执行：A、B一起提交。
  

## 备注
测试结果不符合预期，两个传播机制从结果来看也没有区别，待继续测试，确认是否是环境、参数问题。