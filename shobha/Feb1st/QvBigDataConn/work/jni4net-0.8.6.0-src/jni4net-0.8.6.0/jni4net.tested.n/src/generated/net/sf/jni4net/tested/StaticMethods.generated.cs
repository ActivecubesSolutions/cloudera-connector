//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by jni4net. See http://jni4net.sourceforge.net/ 
//     Runtime Version:2.0.50727.5446
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace net.sf.jni4net.tested {
    
    
    #region Component Designer generated code 
    public partial class StaticMethods_ {
        
        public static global::java.lang.Class _class {
            get {
                return global::net.sf.jni4net.tested.@__StaticMethods.staticClass;
            }
        }
    }
    #endregion
    
    #region Component Designer generated code 
    [global::net.sf.jni4net.attributes.JavaProxyAttribute(typeof(global::net.sf.jni4net.tested.StaticMethods), typeof(global::net.sf.jni4net.tested.StaticMethods_))]
    [global::net.sf.jni4net.attributes.ClrWrapperAttribute(typeof(global::net.sf.jni4net.tested.StaticMethods), typeof(global::net.sf.jni4net.tested.StaticMethods_))]
    internal sealed partial class @__StaticMethods : global::java.lang.Object {
        
        internal new static global::java.lang.Class staticClass;
        
        private @__StaticMethods(global::net.sf.jni4net.jni.JNIEnv @__env) : 
                base(@__env) {
        }
        
        private static void InitJNI(global::net.sf.jni4net.jni.JNIEnv @__env, java.lang.Class @__class) {
            global::net.sf.jni4net.tested.@__StaticMethods.staticClass = @__class;
        }
        
        private static global::System.Collections.Generic.List<global::net.sf.jni4net.jni.JNINativeMethod> @__Init(global::net.sf.jni4net.jni.JNIEnv @__env, global::java.lang.Class @__class) {
            global::System.Type @__type = typeof(__StaticMethods);
            global::System.Collections.Generic.List<global::net.sf.jni4net.jni.JNINativeMethod> methods = new global::System.Collections.Generic.List<global::net.sf.jni4net.jni.JNINativeMethod>();
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "add", "add0", "(II)I"));
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "TestOutParamS", "TestOutParamS1", "(Lnet/sf/jni4net/Out;)V"));
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "TestRefParamS", "TestRefParamS2", "(Lnet/sf/jni4net/Ref;)V"));
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "TestRefParamSa", "TestRefParamSa3", "(Lnet/sf/jni4net/Ref;)V"));
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "TestRefParamPa", "TestRefParamPa4", "(Lnet/sf/jni4net/Ref;)V"));
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "TestRefParamBa", "TestRefParamBa5", "(Lnet/sf/jni4net/Out;IC)V"));
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "TestOutParam", "TestOutParam6", "(Lnet/sf/jni4net/Out;)V"));
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "TestRefParam", "TestRefParam7", "(Lnet/sf/jni4net/Ref;)I"));
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "addEnvDispatcher", "EnvDispatcher8", "(Lnet/sf/jni4net/tested/TestDelegate;)V"));
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "removeEnvDispatcher", "EnvDispatcher9", "(Lnet/sf/jni4net/tested/TestDelegate;)V"));
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "addEnvDispatcherProp", "EnvDispatcherProp10", "(Lnet/sf/jni4net/tested/TestDelegate;)V"));
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "removeEnvDispatcherProp", "EnvDispatcherProp11", "(Lnet/sf/jni4net/tested/TestDelegate;)V"));
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "__ctorStaticMethods0", "__ctorStaticMethods0", "(Lnet/sf/jni4net/inj/IClrProxy;)V"));
            return methods;
        }
        
        private static int add0(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__class, int a, int b) {
            // (II)I
            // (II)I
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            int @__return = default(int);
            try {
            @__return = ((int)(global::net.sf.jni4net.tested.StaticMethods.add(a, b)));
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
            return @__return;
        }
        
        private static void TestOutParamS1(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__class, global::net.sf.jni4net.utils.JniLocalHandle text) {
            // (Lnet/sf/jni4net/Out;)V
            // (Ljava/lang/String;)V
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            try {
            global::java.lang.String @__out_text;
            global::net.sf.jni4net.tested.StaticMethods.TestOutParamS(out __out_text);
            net.sf.jni4net.Out.SetValue<global::java.lang.String>(@__env, text, @__out_text);
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
        }
        
        private static void TestRefParamS2(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__class, global::net.sf.jni4net.utils.JniLocalHandle text) {
            // (Lnet/sf/jni4net/Ref;)V
            // (Ljava/lang/String;)V
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            try {
            global::java.lang.String @__ref_text = net.sf.jni4net.Ref.GetValue<global::java.lang.String>(@__env, text);
            global::net.sf.jni4net.tested.StaticMethods.TestRefParamS(ref __ref_text);
            net.sf.jni4net.Ref.SetValue<global::java.lang.String>(@__env, text, @__ref_text);
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
        }
        
        private static void TestRefParamSa3(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__class, global::net.sf.jni4net.utils.JniLocalHandle text) {
            // (Lnet/sf/jni4net/Ref;)V
            // ([Ljava/lang/String;)V
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            try {
            java.lang.String[] @__ref_text = net.sf.jni4net.Ref.GetValue<java.lang.String[]>(@__env, text);
            global::net.sf.jni4net.tested.StaticMethods.TestRefParamSa(ref __ref_text);
            net.sf.jni4net.Ref.SetValue<java.lang.String[]>(@__env, text, @__ref_text);
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
        }
        
        private static void TestRefParamPa4(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__class, global::net.sf.jni4net.utils.JniLocalHandle text) {
            // (Lnet/sf/jni4net/Ref;)V
            // ([C)V
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            try {
            char[] @__ref_text = net.sf.jni4net.Ref.GetValue<char[]>(@__env, text);
            global::net.sf.jni4net.tested.StaticMethods.TestRefParamPa(ref __ref_text);
            net.sf.jni4net.Ref.SetValue<char[]>(@__env, text, @__ref_text);
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
        }
        
        private static void TestRefParamBa5(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__class, global::net.sf.jni4net.utils.JniLocalHandle text, int a, char f) {
            // (Lnet/sf/jni4net/Out;IC)V
            // ([BIC)V
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            try {
            byte[] @__out_text;
            global::net.sf.jni4net.tested.StaticMethods.TestRefParamBa(out __out_text, a, f);
            net.sf.jni4net.Out.SetValue<byte[]>(@__env, text, @__out_text);
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
        }
        
        private static void TestOutParam6(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__class, global::net.sf.jni4net.utils.JniLocalHandle num) {
            // (Lnet/sf/jni4net/Out;)V
            // (I)V
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            try {
            int @__out_num;
            global::net.sf.jni4net.tested.StaticMethods.TestOutParam(out __out_num);
            net.sf.jni4net.Out.SetValue<int>(@__env, num, @__out_num);
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
        }
        
        private static int TestRefParam7(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__class, global::net.sf.jni4net.utils.JniLocalHandle num) {
            // (Lnet/sf/jni4net/Ref;)I
            // (I)I
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            int @__return = default(int);
            try {
            int @__ref_num = net.sf.jni4net.Ref.GetValue<int>(@__env, num);
            @__return = ((int)(global::net.sf.jni4net.tested.StaticMethods.TestRefParam(ref __ref_num)));
            net.sf.jni4net.Ref.SetValue<int>(@__env, num, @__ref_num);
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
            return @__return;
        }
        
        private static void EnvDispatcher8(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__obj, global::net.sf.jni4net.utils.JniLocalHandle value) {
            // (Lnet/sf/jni4net/tested/TestDelegate;)V
            // (Lnet/sf/jni4net/tested/TestDelegate;)V
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            try {
            global::net.sf.jni4net.tested.StaticMethods @__real = global::net.sf.jni4net.utils.Convertor.StrongJp2C<global::net.sf.jni4net.tested.StaticMethods>(@__env, @__obj);
            @__real.EnvDispatcher += global::net.sf.jni4net.utils.Convertor.StrongJ2CpDelegate<global::net.sf.jni4net.tested.TestDelegate>(@__env, value);
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
        }
        
        private static void EnvDispatcher9(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__obj, global::net.sf.jni4net.utils.JniLocalHandle value) {
            // (Lnet/sf/jni4net/tested/TestDelegate;)V
            // (Lnet/sf/jni4net/tested/TestDelegate;)V
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            try {
            global::net.sf.jni4net.tested.StaticMethods @__real = global::net.sf.jni4net.utils.Convertor.StrongJp2C<global::net.sf.jni4net.tested.StaticMethods>(@__env, @__obj);
            @__real.EnvDispatcher -= global::net.sf.jni4net.utils.Convertor.StrongJ2CpDelegate<global::net.sf.jni4net.tested.TestDelegate>(@__env, value);
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
        }
        
        private static void EnvDispatcherProp10(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__obj, global::net.sf.jni4net.utils.JniLocalHandle value) {
            // (Lnet/sf/jni4net/tested/TestDelegate;)V
            // (Lnet/sf/jni4net/tested/TestDelegate;)V
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            try {
            global::net.sf.jni4net.tested.StaticMethods @__real = global::net.sf.jni4net.utils.Convertor.StrongJp2C<global::net.sf.jni4net.tested.StaticMethods>(@__env, @__obj);
            @__real.EnvDispatcherProp += global::net.sf.jni4net.utils.Convertor.StrongJ2CpDelegate<global::net.sf.jni4net.tested.TestDelegate>(@__env, value);
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
        }
        
        private static void EnvDispatcherProp11(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__obj, global::net.sf.jni4net.utils.JniLocalHandle value) {
            // (Lnet/sf/jni4net/tested/TestDelegate;)V
            // (Lnet/sf/jni4net/tested/TestDelegate;)V
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            try {
            global::net.sf.jni4net.tested.StaticMethods @__real = global::net.sf.jni4net.utils.Convertor.StrongJp2C<global::net.sf.jni4net.tested.StaticMethods>(@__env, @__obj);
            @__real.EnvDispatcherProp -= global::net.sf.jni4net.utils.Convertor.StrongJ2CpDelegate<global::net.sf.jni4net.tested.TestDelegate>(@__env, value);
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
        }
        
        private static void @__ctorStaticMethods0(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__class, global::net.sf.jni4net.utils.JniLocalHandle @__obj) {
            // ()V
            // ()V
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            try {
            global::net.sf.jni4net.tested.StaticMethods @__real = new global::net.sf.jni4net.tested.StaticMethods();
            global::net.sf.jni4net.utils.Convertor.InitProxy(@__env, @__obj, @__real);
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
        }
        
        new internal sealed class ContructionHelper : global::net.sf.jni4net.utils.IConstructionHelper {
            
            public global::net.sf.jni4net.jni.IJvmProxy CreateProxy(global::net.sf.jni4net.jni.JNIEnv @__env) {
                return new global::net.sf.jni4net.tested.@__StaticMethods(@__env);
            }
        }
    }
    #endregion
}
