package cn.stylefeng.guns.system;

import cn.stylefeng.guns.base.BaseJunit;
import cn.stylefeng.guns.modular.system.entity.Dept;
import cn.stylefeng.guns.modular.system.mapper.DeptMapper;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * 字典服务测试
 *
 * @author fengshuonan
 * @date 2017-04-27 17:05
 */
public class DeptTest extends BaseJunit {

    @Resource
    DeptMapper deptMapper;

    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

    public void executeLog(TimerTask task) {
        executor.schedule(task, 10, TimeUnit.MILLISECONDS);
    }
    public static void test1(){

        try {

            Thread thread = new Thread();
            thread.start();
            Integer.compare(1,2);
            Object[] t = new Object[15];
            t.wait(1000);
            t[0] = "123";
            t[1] = new Integer(10);
            t[2] = null;
            System.out.println(t[0]);
            System.out.println(t[1]);
            System.out.println(t[2]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            test1();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Integer aa = 126;
        Integer bb = 126;
        System.out.println(aa.hashCode());
        System.out.println(bb.hashCode());
        System.out.println(aa == bb);

        String a = new String("asd");
        String b = new String("asd");
        a.equals(b);
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(a == b);
    }

    @Test
    public void addDeptTest() {


        Integer aa = 126;
        Integer bb = 126;
        System.out.println(aa.hashCode());
        System.out.println(bb.hashCode());
        System.out.println(aa == bb);

         String a = new String("asd");
         String b = new String("asd");
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(a == b);


//
//        Dept dept = new Dept();
//        dept.setFullName("测试fullname");
//        dept.setSort(5);
//        dept.setPid(1L);
//        dept.setSimpleName("测试");
//        dept.setDescription("测试tips");
//        dept.setVersion(1);
//        Integer insert = deptMapper.insert(dept);
//        assertEquals(insert, new Integer(1));
    }

    @Test
    public void updateTest() {
        Dept dept = this.deptMapper.selectById(24);
        dept.setDescription("哈哈");
        deptMapper.updateById(dept);
    }

    @Test
    public void deleteTest() {
        Dept dept = this.deptMapper.selectById(24);
        Integer integer = deptMapper.deleteById(dept);
        assertTrue(integer > 0);
    }
}
