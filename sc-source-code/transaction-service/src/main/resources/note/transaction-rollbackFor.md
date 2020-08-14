# 实验测试method2异常情况对事务的影响
**共两组用例，method2分别抛出检查型异常`(用例：SQLException)`和非检查型异常`(用例：ArithmeticException)`**

## 一、不加事务
```text
public void transaction() {
    method1();
    method2();
}
public void method1() {}
public void method2() {
    int i = 1 / 0;
}
```
- SQLException：不影响method1的执行结果。
- ArithmeticException：不影响method1的执行结果。

## 二、加事务
### 2.1、transaction()加事务`(默认传播机制Propagation.REQUIRED)`
#### ①、不捕获异常
```text
@Transactional
public void transaction() {
    method1();
    method2();
}
public void method1() {}
public void method2() {}
```
- SQLException：事务不回滚，method1执行成功。
- ArithmeticException：事务回滚，method1执行失败。

#### ②、捕获异常但不处理
```text
@Transactional
public void transaction() {
    method1();
    try {
        method2();
    } catch (Exception e) {
        // do nothing
    }
}
public void method1() {}
public void method2() {}
```
- SQLException：事务不回滚，method1执行成功。
- ArithmeticException：事务不回滚，method1执行成功。

#### ③、捕获异常并抛出非检查型异常
```text
@Transactional
public void transaction() {
    method1();
    try {
        method2();
    } catch (Exception e) {
        throw new RuntimeException();
        // 或
        // throw new Error();
    }
}
public void method1() {}
public void method2() {}
```
- SQLException：事务回滚，method1执行失败。
- ArithmeticException：事务回滚，method1执行失败。

#### ④、捕获异常并抛出检查型异常
```text
@Transactional
public void transaction() {
    method1();
    try {
        method2();
    } catch (Exception e) {
        throw new Exception();
    }
}
public void method1() {}
public void method2() {}
```
- SQLException：事务不回滚，method1执行成功。
- ArithmeticException：事务不回滚，method1执行成功。

#### ⑤、设置rollbackFor为Exception，捕获异常并抛出检查型异常
```text
@Transactional(rollbackFor = Exception.class)
public void transaction() {
    method1();
    try {
        method2();
    } catch (Exception e) {
        throw new Exception();
    }
}
public void method1() {}
public void method2() {}
```
- SQLException：事务回滚，method1执行失败。
- ArithmeticException：事务回滚，method1执行失败。

#### 小结
- 默认配置下，抛出非检查型异常(`RuntimeException及其子类和Error错误`)会导致事务回滚。
- 默认配置下，抛出检查型异常(`Exception下除RuntimeException外的异常`)不会导致事务回滚。
- 要求配置事务的rollbackFor为Exception。