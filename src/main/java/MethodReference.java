public class MethodReference {

    public  MethodReference(){
        System.out.println("in constructor");
    }
    public  static void method1(){
        System.out.println("static method 1 reference");
    }

    public  void method2(){
        System.out.println("static method 2 reference");
    }

    public  static MethodReference method3(){
        System.out.println("static method 3 reference");
        return new MethodReference();
    }

}

