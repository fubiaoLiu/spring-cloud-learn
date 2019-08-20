package com.xiaoliu.learn.demo.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @description: 测试mock的相关方法
 * @author: FuBiaoLiu
 * @date: 2019/8/20
 */
@RunWith(MockitoJUnitRunner.class)
public class FuncTest {

    /**
     * 测试方法的执行顺序
     */
    @Test
    public void testMockMethodInOrder() {
        ClassDemo class1 = Mockito.mock(ClassDemo.class);
        ClassDemo class2 = Mockito.mock(ClassDemo.class);

        Mockito.when(class1.hello("xiaoliu")).thenReturn("hello:xiaoliu");
        Mockito.when(class2.hello("xiaoliu")).thenReturn("hello:xiaoliu");

        // 这里决定了 class1在先
        Object hello1 = class1.hello("xiaoliu");
        Assert.assertEquals("hello:xiaoliu", hello1);
        // 这里决定了 class2在后
        Object hello2 = class2.hello("xiaoliu");
        Assert.assertEquals("hello:xiaoliu", hello2);

        // 此行并不决定顺序，下面的两行才开始验证顺序
        InOrder inOrder = Mockito.inOrder(class1, class2);
        inOrder.verify(class1).hello("xiaoliu");
        inOrder.verify(class2).hello("xiaoliu");
    }

    /**
     * 非局部模拟（只能通过When().thenReturn() 来指定函数的返回类型，但是是调用不了 模拟出来的的类的方法的）
     */
    @Test
    public void testSkipExpect() {
        ClassDemo obj = Mockito.mock(ClassDemo.class);

        // 如：正常如果hello方法被调用，应该返回z3，但这里返回的null ，说明该方法是没有被调用的
        // 因为我们模拟出来的是非局部变量
        Assert.assertNull(obj.hello("xiaoliu"));
        // show方法也是一样，show方法如果执行应该打印一句话，但是obj并不能执行show方法
        obj.show();
        System.out.println(obj.hello("xiaoliu"));

        Mockito.verify(obj).show();
        Mockito.verify(obj, Mockito.times(2)).hello("xiaoliu");
    }

    /**
     * 局部模拟 - spy()方式
     */
    @Test
    public void testSpy() {
        ClassDemo obj = Mockito.spy(new ClassDemo());

        // 有了这行，show方法被执行就不会有任何操作
        Mockito.doNothing().when(obj).show();

        Assert.assertEquals("hello:xiaoliu", obj.hello("xiaoliu"));
        obj.show();

        Mockito.verify(obj).hello("xiaoliu");
        Mockito.verify(obj).show();
    }

    /**
     * 值得注意的陷阱
     */
    @Test
    public void testSpy2() {
        ClassDemo obj = Mockito.spy(new ClassDemo());
        // 即使使用的是when thenReturn，但是仍然会执行方法里面的内容
        Mockito.when(obj.hello("xiaoliu")).thenReturn("xiaoliu");

        Assert.assertEquals("hello:xiaoliu", obj.hello("xiaoliu"));

        Mockito.verify(obj).hello("xiaoliu");
    }

    /**
     * 如果既想使用局部模拟，又不想调用到方法里面的内容
     */
    @Test
    public void testSpy3() {
        ClassDemo obj = Mockito.spy(new ClassDemo());

        Mockito.doReturn("xiaoliu").when(obj).hello("xiaoliu");

        Assert.assertEquals("xiaoliu", obj.hello("xiaoliu"));

        Mockito.verify(obj).hello("xiaoliu");
    }
}
