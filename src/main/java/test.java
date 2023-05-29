public class test {
    public static void main(String[] args) {

//        Runnable runnable = new MethodReference()::method2;
//        runnable.run();
        Runnable runnable = MethodReference::method3;
        runnable.run();

    }
}
