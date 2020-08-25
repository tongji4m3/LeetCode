public class MainTest
{
    static abstract class Human
    {
    }
    static class Man extends Human
    {
    }
    static class Woman extends Human
    {
    }

    public void sayHello(Human guy)
    {
        System.out.println("Human");
    }
    public void sayHello(Man guy)
    {
        System.out.println("Man");
    }
    public void sayHello(Woman guy)
    {
        System.out.println("Women");
    }

    public static void main(String[] args)
    {
        Human man = new Man();
        Human women = new Woman();
        MainTest mainTest = new MainTest();
        mainTest.sayHello((Man)man);
        mainTest.sayHello(women);
        /*
        result:
            Human
            Human
         */
    }
}
