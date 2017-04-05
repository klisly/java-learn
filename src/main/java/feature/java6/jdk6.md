JDK6的新特性 
JDK6的新特性之一_Desktop类和SystemTray类 
JDK6的新特性之七_用Console开发控制台程序 
JDK6的新特性之三_理解StAX 
JDK6的新特性之九_CommonAnnotations 
JDK6的新特性之二_使用JAXB2来实现对象与XML之间的映射 
JDK6的新特性之五_轻量级HttpServer 
JDK6的新特性之八_嵌入式数据库Derby 
JDK6的新特性之六_插入式注解处理API 
JDK6的新特性之十_Web服务元数据 
JDK6的新特性之十一_更简单强大的JAX-WS 
JDK6的新特性之十三_JTable的排序和过滤 
JDK6的新特性之十二_脚本语言支持 
JDK6的新特性之四_使用Compiler API 

JDK6的新特性之一_Desktop类和SystemTray类 

JDK6.0发布有段时间了，新的JDK也有不少新的特性，我去网上搜集了一下，列在下面和大家一起学习． 
１．Desktop和SystemTray. 在JDK6中 ,AWT新增加了两个类:Desktop和SystemTray,前者可以用来打开系统默认浏览器浏览指定的URL,打开系统默认邮件客户端给指定的邮箱发邮件,用默认应用程序打开或编辑文件(比如,用记事本打开以txt为后缀名的文件),用系统默认的打印机打印文档;后者可以用来在系统托盘区创建一个托盘程序。 

我随便找了几张图，在Tray里面都是空的，没有图,可能是图太大，有xdjm知道希望告诉我． 

Java代码  收藏代码
import java.awt.AWTException;  
import java.awt.Desktop;  
import java.awt.Image;  
import java.awt.MenuItem;  
import java.awt.PopupMenu;  
import java.awt.SystemTray;  
import java.awt.Toolkit;  
import java.awt.TrayIcon;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.io.File;  
import java.io.IOException;  
import java.net.URI;  
import java.net.URISyntaxException;  
  
public class DesktopTrayTest{  
    private static Desktop desktop;  
    private static SystemTray st;  
    private static PopupMenu pm;  
      
    public static void main( String[] args ) {  
        if( Desktop.isDesktopSupported() ) {  
            desktop = Desktop.getDesktop();  
        }  
        if( SystemTray.isSupported() ) {  
            st = SystemTray.getSystemTray();  
            Image image = Toolkit.getDefaultToolkit().createImage( "http://www.51ppt.com.cn/Article/Uploadphotos/200604/20064147333288.png" );  
            createPopupMenu();  
            TrayIcon ti = new TrayIcon( image, "Demo", pm );  
            try{  
                st.add( ti );  
            } catch( AWTException awte ) {  
                awte.printStackTrace();  
            }  
        }  
    }  
    public static void sendMail( String mail ) {  
        if( desktop != null &&  
            desktop.isSupported( Desktop.Action.MAIL ) ) {  
            try {  
                desktop.mail( new URI( mail ) );  
            } catch (IOException e) {  
                e.printStackTrace();  
            } catch (URISyntaxException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    public static void openBrowser( String url ) {  
        if( desktop != null &&  
            desktop.isSupported( Desktop.Action.BROWSE )) {  
            try {  
                desktop.browse( new URI( url ) );  
            } catch (IOException e) {  
                e.printStackTrace();  
            } catch (URISyntaxException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    public static void edit() {  
        if( desktop != null &&  
            desktop.isSupported( Desktop.Action.EDIT ) ) {  
            File file = new File( "test.txt" );  
            try {  
                if( file.exists() == false ) {  
                    file.create();  
                }  
                desktop.edit( file );  
            } catch( IOException ioe ) {  
                ioe.printStackTrace();  
            }  
        }  
    }  
    public static void createPopupMenu() {  
        pm = new PopupMenu();  
        MenuItem ob = new MenuItem( "Open url" );  
        ob.addActionListener( new ActionListener() {  
            public void actionPerformed( ActionEvent ae ) {  
                openBrowser( "http://blog.csdn.net/xumingming64398966" );  
            }  
        });  
        MenuItem sm = new MenuItem( "Send Mail" );  
        sm.addActionListener( new ActionListener() {  
            public void actionPerformed( ActionEvent ae ) {  
                sendMail( "64398966@qq.com" );  
            }  
        });  
        MenuItem ed = new MenuItem( "Edit" );  
        ed.addActionListener( new ActionListener() {  
            public void actionPerformed( ActionEvent ae ) {  
                edit();  
            }  
        });  
        MenuItem ex = new MenuItem( "Exit" );  
        ex.addActionListener( new ActionListener() {  
            public void actionPerformed( ActionEvent ae ) {  
                System.exit( 0 );  
            }  
        });  
        pm.add( ob );  
        pm.add( sm );  
        pm.add( ed );  
        pm.addSeparator();  
        pm.add( ex );  
    }  
}  
２．Console. JDK6中提供了java.io.Console类专用来访问基于字符的控制台设备. 你的程序如果要与Windows下的cmd或者Linux下的Terminal交互,就可以用Console类代劳. 但我们不总是能得到可用的Console, 一个JVM是否有可用的Console依赖于底层平台和JVM如何被调用. 如果JVM是在交互式命令行(比如Windows的cmd)中启动的,并且输入输出没有重定向到另外的地方,那么就可以得到一个可用的Console实例. 下面代码演示了Console类的用法:
 
Java代码  收藏代码
import java.io.Console;  
  
public class ConsoleTest {  
    public static void main( String[] args ) {  
        Console console = System.console();  
        if( console != null ) {  
            String user = new String( console.readLine( "Enter User:", new Object[ 0 ] ) );  
            String pwd = new String( console.readPassword( "Enter Password:", new Object[ 0 ] ));  
            console.printf( "User name is:%s", new Object[]{user} );  
            console.printf( "Password is:%s", new Object[]{pwd} );  
        } else {  
            System.out.println( "No Console!" );  
        }  
    }  
}  
 
你如果是在一个IDE中如eclipse, netbeans中运行你将得到：
No Console!
因为只有在命令行中才能得到Console对象。
 
３．Compiler API. 现在我们可以用JDK6 的Compiler API(JSR 199)去动态编译Java源文件，Compiler API结合反射功能就可以实现动态的产生Java代码并编译执行这些代码，有点动态语言的特征。这个特性对于某些需要用到动态编译的应用程序相当有用，比如JSP Web Server，当我们手动修改JSP后，是不希望需要重启Web Server才可以看到效果的，这时候我们就可以用Compiler API来实现动态编译JSP文件，当然，现在的JSP Web Server也是支持JSP热部署的，现在的JSP Web Server通过在运行期间通过Runtime.exec或ProcessBuilder来调用javac来编译代码，这种方式需要我们产生另一个进程去做编译工作，不够优雅而且容易使代码依赖与特定的操作系统；Compiler API通过一套易用的标准的API提供了更加丰富的方式去做动态编译,而且是跨平台的。 下面代码演示了Compiler API的使用：
 
Java代码  收藏代码
import java.io.BufferedWriter;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.util.Iterator;  
  
import javax.tools.JavaCompiler;  
import javax.tools.JavaFileObject;  
import javax.tools.StandardJavaFileManager;  
import javax.tools.ToolProvider;  
  
public class CompilerAPITest {  
    private final static String srcFileName = "Test.java";  
    private final static String classFileName = "Test.class";  
    private final static String className = "Test";  
      
    public static void main( String[] args ) {  
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();  
        if( compiler == null ) {  
            System.err.println( "Compiler is null!" );  
            return;  
        }  
        StandardJavaFileManager fileManager = compiler.getStandardFileManager( null, null, null );  
        generateJavaClass();  
          
        Iterable < ? extends JavaFileObject> sourceFiles = fileManager.getJavaFileObjects( new String[]{ srcFileName } );  
        compiler.getTask( null, fileManager, null, null, null, sourceFiles ).call();  
        try {  
            fileManager.close();  
            Class.forName( className ).newInstance();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (InstantiationException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public static void generateJavaClass() {  
        try {  
            FileWriter rw = new FileWriter( srcFileName );  
            BufferedWriter bw = new BufferedWriter( rw );  
            bw.write( "public class " + className + " {" );  
            bw.newLine();  
              
            bw.write( "public " + className + "() {");  
            bw.newLine();  
            bw.write( "System.out.println( 'you are in the constructor of Class Test' );" );  
            bw.write( "}" );  
            bw.newLine();  
              
            bw.write( "}" );  
            bw.flush();  
            bw.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  
 
JDK1.6.0新特性详解与代码示例
 
JDK6.0发布有段时间了，新的JDK也有不少新的特性，我去网上搜集了一下，列在下面和大家一起学习．
１．Desktop和SystemTray. 在JDK6中 ,AWT新增加了两个类:Desktop和SystemTray,前者可以用来打开系统默认浏览器浏览指定的URL,打开系统默认邮件客户端给指定的邮箱发邮件,用默认应用程序打开或编辑文件(比如,用记事本打开以txt为后缀名的文件),用系统默认的打印机打印文档;后者可以用来在系统托盘区创建一个托盘程序。
我随便找了几张图，在Tray里面都是空的，没有图,可能是图太大，有xdjm知道希望告诉我．

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
public class DesktopTrayTest {
    private static Desktop desktop;
    private static SystemTray st;
    private static PopupMenu pm;
    
    public static void main( String[] args ) {
        if( Desktop.isDesktopSupported() ) {
            desktop = Desktop.getDesktop();
        }
        if( SystemTray.isSupported() ) {
            st = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage( "http://www.51ppt.com.cn/Article/Uploadphotos/200604/20064147333288.png" );
            createPopupMenu();
            TrayIcon ti = new TrayIcon( image, "Demo", pm );
            try{
                st.add( ti );
            } catch( AWTException awte ) {
                awte.printStackTrace();
            }
        }
    }
    public static void sendMail( String mail ) {
        if( desktop != null &&
            desktop.isSupported( Desktop.Action.MAIL ) ) {
            try {
                desktop.mail( new URI( mail ) );
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
    public static void openBrowser( String url ) {
        if( desktop != null &&
            desktop.isSupported( Desktop.Action.BROWSE )) {
            try {
                desktop.browse( new URI( url ) );
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
    public static void edit() {
        if( desktop != null &&
            desktop.isSupported( Desktop.Action.EDIT ) ) {
            File file = new File( "test.txt" );
            try {
                if( file.exists() == false ) {
                    file.create();
                }
                desktop.edit( file );
            } catch( IOException ioe ) {
                ioe.printStackTrace();
            }
        }
    }
    public static void createPopupMenu() {
        pm = new PopupMenu();
        MenuItem ob = new MenuItem( "Open url" );
        ob.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                openBrowser( "http://blog.csdn.net/xumingming64398966" );
            }
        });
        MenuItem sm = new MenuItem( "Send Mail" );
        sm.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                sendMail( "64398966@qq.com" );
            }
        });
        MenuItem ed = new MenuItem( "Edit" );
        ed.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                edit();
            }
        });
        MenuItem ex = new MenuItem( "Exit" );
        ex.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                System.exit( 0 );
            }
        });
        pm.add( ob );
        pm.add( sm );
        pm.add( ed );
        pm.addSeparator();
        pm.add( ex );
    }
}
 
２．Console. JDK6中提供了java.io.Console类专用来访问基于字符的控制台设备. 你的程序如果要与Windows下的cmd或者Linux下的Terminal交互,就可以用Console类代劳. 但我们不总是能得到可用的Console, 一个JVM是否有可用的Console依赖于底层平台和JVM如何被调用. 如果JVM是在交互式命令行(比如Windows的cmd)中启动的,并且输入输出没有重定向到另外的地方,那么就可以得到一个可用的Console实例. 下面代码演示了Console类的用法:

import java.io.Console;
public class ConsoleTest {
    public static void main( String[] args ) {
        Console console = System.console();
        if( console != null ) {
            String user = new String( console.readLine( "Enter User:", new Object[ 0 ] ) );
            String pwd = new String( console.readPassword( "Enter Password:", new Object[ 0 ] ));
            console.printf( "User name is:%s", new Object[]{user} );
            console.printf( "Password is:%s", new Object[]{pwd} );
        } else {
            System.out.println( "No Console!" );
        }
    }
}

你如果是在一个IDE中如eclipse, netbeans中运行你将得到：
No Console!
因为只有在命令行中才能得到Console对象。
３．Compiler API. 现在我们可以用JDK6 的Compiler API(JSR 199)去动态编译Java源文件，Compiler API结合反射功能就可以实现动态的产生Java代码并编译执行这些代码，有点动态语言的特征。这个特性对于某些需要用到动态编译的应用程序相当有用，比如JSP Web Server，当我们手动修改JSP后，是不希望需要重启Web Server才可以看到效果的，这时候我们就可以用Compiler API来实现动态编译JSP文件，当然，现在的JSP Web Server也是支持JSP热部署的，现在的JSP Web Server通过在运行期间通过Runtime.exec或ProcessBuilder来调用javac来编译代码，这种方式需要我们产生另一个进程去做编译工作，不够优雅而且容易使代码依赖与特定的操作系统；Compiler API通过一套易用的标准的API提供了更加丰富的方式去做动态编译,而且是跨平台的。 下面代码演示了Compiler API的使用：

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
public class CompilerAPITest {
    private final static String srcFileName = "Test.java";
    private final static String classFileName = "Test.class";
    private final static String className = "Test";
    
    public static void main( String[] args ) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if( compiler == null ) {
            System.err.println( "Compiler is null!" );
            return;
        }
        StandardJavaFileManager fileManager = compiler.getStandardFileManager( null, null, null );
        generateJavaClass();
        
        Iterable < ? extends JavaFileObject> sourceFiles = fileManager.getJavaFileObjects( new String[]{ srcFileName } );
        compiler.getTask( null, fileManager, null, null, null, sourceFiles ).call();
        try {
            fileManager.close();
            Class.forName( className ).newInstance();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void generateJavaClass() {
        try {
            FileWriter rw = new FileWriter( srcFileName );
            BufferedWriter bw = new BufferedWriter( rw );
            bw.write( "public class " + className + " {" );
            bw.newLine();
            
            bw.write( "public " + className + "() {");
            bw.newLine();
            bw.write( "System.out.println( 'you are in the constructor of Class Test' );" );
            bw.write( "}" );
            bw.newLine();
            
            bw.write( "}" );
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
我在运行这个例子的时候发现ToolProvider.getSystemJavaCompiler得到的是NULL,后来上网一查，原来是一个Bug!链接如下：
http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6477844
Closed, not reproducible
那为什么我一直在reproduce阿？
4.Http Server API. JDK6提供了一个简单的Http Server API,据此我们可以构建自己的嵌入式Http Server,它支持Http和Https协议,提供了HTTP1.1的部分实现，没有被实现的那部分可以通过扩展已有的Http Server API来实现,程序员必须自己实现HttpHandler接口,HttpServer会调用HttpHandler实现类的回调方法来处理客户端请求,在这里,我们把一个Http请求和它的响应称为一个交换,包装成HttpExchange类,HttpServer负责将HttpExchange传给 HttpHandler实现类的回调方法.下面代码演示了怎样创建自己的Http Server .
Java代码  收藏代码
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.InetSocketAddress;  
  
import com.sun.net.httpserver.HttpExchange;  
import com.sun.net.httpserver.HttpHandler;  
import com.sun.net.httpserver.HttpServer;  
  
public class HttpServerAPITest {  
    private static int count = 0;  
    public static void main( String[] args ) {  
        try {  
            HttpServer hs = HttpServer.create( new InetSocketAddress( 8888 ), 0 );  
            hs.createContext( "/", new MyHandler() );  
            hs.createContext( "/java", new MyHandler() );  
            hs.setExecutor( null );  
            hs.start();  
            System.out.println( "---begin---" );  
            System.out.println( "Listening on " + hs.getAddress() );  
        } catch( IOException ioe ) {  
            ioe.printStackTrace();  
        }  
    }  
    static class MyHandler implements HttpHandler {  
        public void handle( HttpExchange he ) throws IOException {  
            System.out.println( "Request " + count++  );  
            System.out.println( he.getHttpContext().getPath() );  
              
            InputStream is = he.getRequestBody();  
            String response = "<font color='blue'>Happy Spring Festerval</font>";  
            he.sendResponseHeaders( 200, response.length() );  
            OutputStream os = he.getResponseBody();  
            os.write( response.getBytes() );  
            os.close();  
        }  
    }  
}  
 
5.对脚本语言的支持如: ruby, groovy, javascript.
Java代码  收藏代码
import java.io.FileReader;  
  
import javax.script.Invocable;  
import javax.script.ScriptEngine;  
import javax.script.ScriptEngineManager;  
  
public class ScriptTest {  
    public static void main( String[] args ) {  
        ScriptEngineManager manager = new ScriptEngineManager();  
        ScriptEngine engine = manager.getEngineByName( "ECMAScript" );  
        try {  
            engine.eval( new FileReader( "C:\test.js" ) );  
            Invocable invocableEngine = (Invocable)engine;  
            Object ret = invocableEngine.invokeFunction( "test", null );  
            System.out.println( "The result is :" + (Double)ret );  
        } catch( Exception e ) {  
            e.printStackTrace();  
        }  
    }  
}  
  
test.js如下:  
  
function test(){  
    return Math.round( 11.2 );  
}  
 6.插入式注解处理API(Pluggable Annotation Processing API)，插入式注解处理API(JSR 269)提供一套标准API来处理Annotations.JSR 269用Annotation Processor在编译期间而不是运行期间处理Annotation, Annotation Processor相当于编译器的一个插件,所以称为插入式注解处理.如果Annotation Processor处理Annotation时(执行process方法)产生了新的Java代码,编译器会再调用一次Annotation Processor,如果第二次处理还有新代码产生,就会接着调用Annotation Processor,直到没有新代码产生为止.每执行一次process()方法被称为一个"round",这样整个Annotation processing过程可以看作是一个round的序列. 
举个例子：们想建立一套基于Annotation的单元测试框架(如TestNG),在测试类里面用Annotation来标识测试期间需要执行的测试方法,如下所示:
Java代码  收藏代码
@TestMethod  
 public void testCheckName(){  
       //do something here  
 }  
 
这时我们就可以用JSR 269提供的API来处理测试类,根据Annotation提取出需要执行的测试方法.

再举个例子: 下面我用代码演示如何来用JSR 269提供的API来处理Annotations和读取Java源文件的元数据(metadata)
Java代码  收藏代码
import java.util.List;  
import java.util.Map;  
import java.util.Set;  
  
import javax.annotation.processing.AbstractProcessor;  
import javax.annotation.processing.RoundEnvironment;  
import javax.annotation.processing.SupportedAnnotationTypes;  
import javax.annotation.processing.SupportedSourceVersion;  
import javax.lang.model.SourceVersion;  
import javax.lang.model.element.AnnotationMirror;  
import javax.lang.model.element.AnnotationValue;  
import javax.lang.model.element.Element;  
import javax.lang.model.element.ExecutableElement;  
import javax.lang.model.element.TypeElement;  
import javax.lang.model.util.ElementFilter;  
import javax.tools.Diagnostic.Kind;  
  
@SupportedAnnotationTypes( "ToBeTested" )  
@SupportedSourceVersion( SourceVersion.RELEASE_6 )  
public class MyAnnotationProcessor extends AbstractProcessor {  
    private void note( String msg ) {  
        processingEnv.getMessager().printMessage( Kind.NOTE, msg );  
    }  
    public boolean process( Set< ? extends TypeElement > annotations, RoundEnvironment roundEnv ) {  
        for( TypeElement te : annotations ) {  
            note( "annotation: " + te.toString() );  
        }  
        Set< ? extends Element > elements = roundEnv.getRootElements();  
        for( Element e : elements ) {  
            List< ? extends Element > enclosedElems = e.getEnclosedElements();  
            List< ? extends ExecutableElement > ees = ElementFilter.methodsIn( enclosedElems );  
            for( ExecutableElement ee : ees ) {  
                note( "Executable Element Name: " + ee.getSimpleName() );  
                List< ? extends AnnotationMirror > as = ee.getAnnotationMirrors();  
                note( " as: " + as );   
                for( AnnotationMirror am : as ){  
                    Map< ? extends ExecutableElement, ? extends AnnotationValue > map = am.getElementValues();  
                    Set< ? extends ExecutableElement > ks = map.keySet();  
                    for( ExecutableElement k : ks ) {  
                        AnnotationValue av = map.get( k );  
                        note("----"+ee.getSimpleName()+"."+k.getSimpleName()+"="+av.getValue());  
                    }  
                }  
            }  
        }  
        return false;  
    }  
}  
  
   
  
  
public class Testing {  
    @ToBeTested(group="A")  
    public void m1(){  
    }  
    @ToBeTested(group="B",owner="QQ")  
    public void m2(){  
    }      
}  
  
   
  
import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
@Retention( RetentionPolicy.RUNTIME )  
@Target( ElementType.METHOD )  
public @interface ToBeTested {  
    String owner() default "Chinajash";  
    String group();  
}  
 7.StAX. StAX是The Streaming API for XML的缩写，是继DOM(Document Object Model)和SAX(Simple API for XML)之后的又一种处理xml的api,一种利用拉模式解析(pull-parsing)XML文档的API.StAX通过提供一种基于事件迭代器(Iterator)的API让程序员去控制xml文档解析过程,程序遍历这个事件迭代器去处理每一个解析事件，解析事件可以看做是程序拉出来的，也就是程序促使解析器产生一个解析事件然后处理该事件，之后又促使解析器产生下一个解析事件，如此循环直到碰到文档结束符；SAX也是基于事件处理xml文档，但却是用推模式解析，解析器解析完整个xml文档后，才产生解析事件，然后推给程序去处理这些事件；DOM采用的方式是将整个xml文档映射到一颗内存树，这样就可以很容易地得到父节点和子结点以及兄弟节点的数据，但如果文档很大，将会严重影响性能。
下面是个例子：
Java代码  收藏代码
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
  
import javax.xml.namespace.QName;  
import javax.xml.stream.XMLEventReader;  
import javax.xml.stream.XMLInputFactory;  
import javax.xml.stream.XMLOutputFactory;  
import javax.xml.stream.XMLStreamException;  
import javax.xml.stream.XMLStreamWriter;  
import javax.xml.stream.events.StartElement;  
import javax.xml.stream.events.XMLEvent;  
  
public class StaxTest {  
    public static void main( String[] arg ) throws XMLStreamException, FileNotFoundException {  
        readXMLByStAX();  
        writeXMLByStAX();  
    }  
    public static void readXMLByStAX() throws XMLStreamException, FileNotFoundException {  
        XMLInputFactory factory = XMLInputFactory.newInstance();  
        XMLEventReader  reader = factory.createXMLEventReader( StaxTest.class.getResourceAsStream( "test.xml" ) );  
        XMLEvent event;  
        StringBuffer  parsingResult = new StringBuffer();  
        while( reader.hasNext() ) {  
            event = reader.nextEvent();  
            if( event.isStartElement() ) {  
                StartElement se = event.asStartElement();  
                parsingResult.append( "<" );  
                parsingResult.append( se.getName() );  
                if( se.getName().getLocalPart().equals( "catalog" ) ) {  
                    parsingResult.append( "id="" );  
                    parsingResult.append( se.getAttributeByName( new QName( "id" ) ).getValue());  
                    parsingResult.append( """ ) ;  
                }  
                parsingResult.append( ">" );  
            } else if( event.isCharacters() ) {  
                parsingResult.append( event.asCharacters().getData() );  
            } else if( event.isEndElement() ) {  
                parsingResult.append( "</" );  
                parsingResult.append( event.asEndElement().getName() );  
                parsingResult.append( ">" );  
            }  
        }  
        System.out.println( parsingResult );  
    }  
      
    public static void writeXMLByStAX() throws XMLStreamException, FileNotFoundException {  
        XMLOutputFactory factory = XMLOutputFactory.newInstance();  
        XMLStreamWriter writer = factory.createXMLStreamWriter( new FileOutputStream( "output.xml" ) );  
        writer.writeStartDocument();  
        writer.writeCharacters( " " );  
        writer.writeComment( "testing comment" );  
        writer.writeCharacters( " " );  
        writer.writeStartElement( "catalogs" );  
        writer.writeNamespace( "myNS", "http://blog.csdn.net/Chinajash" );  
        writer.writeAttribute( "owner", "sina" );  
        writer.writeCharacters( " " );  
        writer.writeStartElement("http://blog.csdn.net/Chinajash", "catalog");  
        writer.writeAttribute("id","007");  
        writer.writeCharacters("Apparel");  
        // 写入catalog元素的结束标签  
        writer.writeEndElement();  
        // 写入catalogs元素的结束标签  
        writer.writeEndElement();  
        // 结束 XML 文档  
        writer.writeEndDocument();           
        writer.close();  
  
    }  
}  
  
test.xml:  
  
   
  
<?xml version="1.0" encoding="UTF-8"?>  
<catalogs>  
    <catalog id="001">Book</catalog>  
    <catalog id="002">Video</catalog>  
</catalogs>  
 
8. Web Service. 由于Web服务日趋流行，利用Web服务的功能性的API特征正从最新的Java EE版本中向Java SE 6平台迁移。换言之，针对Web服务不需另外加入额外的工具，在Java EE和Java SE平台拥有相同的API。野马将大把不同的Web服务相关的API加到标准的工具柜中：以JSR 181针对Java 平台的Web服务元数据，通过JSR 224的基于XML 的Web服务Java API（JAX-WS）；针对Java的带有附件的SOAP API（SAAJ）作为JSR 67 。与三个Web服务API相关的包新增到Java SE 6.0里：JAX－WS API 放置到javax.xml.ws包； SAAJ类在javax.xml.soap 包； Web服务的元数据类放置在javax.jws包里。 下面是一个简单的例子，　下面的代码是要作为web service发布的类。
Java代码  收藏代码
package hello;  
import javax.jws.WebService;  
import javax.xml.ws.Endpoint;  
  
@WebService  
public class CircleFunctions {  
    public double getArea( int radius ) {  
        return Math.PI * radius * radius;  
    }  
    public double getCircumference( int radius ) {  
        return Math.PI * radius * 2;  
    }  
      
    public static void main( String[] args ) {  
        Endpoint.publish( "http://localhost:8888/WebServiceExample/circlefunctions", new CircleFunctions());  
    }  
}  
 
处理的方法如下：
javac -d ./ CircleFunctions.java
wsgen hello.CircleFunctions
java hello.CircleFunctions
然后在浏览器中输入如下url,你将得到一个xml页面：
http: //localhost:8888/WebServiceExample/circlefunctions?WSDL
参考网页：
1.Desktop和SystemTray. http://dev.yesky.com/411/3019911.shtml
2.Console. http://dev.yesky.com/133/3032133.shtml
3.Compiler API. http://developer.51cto.com/art/200701/37359.htm
4.HttpServer API. http://www.testage.net/QA/Dev/200701/1396.htm
5. 对脚本语言的支持http://blog.edwardro.com/read.php?167
6. 插入式注解处理API. http://ourconan.com.cn/article.php?itemid-2113-type-blog.html
7.StAX. http://ourconan.com.cn/article.php?itemid-2111-type-blog.html
8.Web Service. http://www.360doc.com/showWeb/0/0/298124.aspx
9.JDK1.5的Annotation
  http://lzqdiy.bokee.com/viewdiary.14724866.html