//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by jni4net. See http://jni4net.sourceforge.net/ 
//     Runtime Version:4.0.30319.17929
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace mojohive {
    
    
    #region Component Designer generated code 
    [global::net.sf.jni4net.attributes.JavaClassAttribute()]
    public partial class MojoHiveDriver : global::java.lang.Object, global::mojohive.IMojoHiveDriver {
        
        internal new static global::java.lang.Class staticClass;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_TestConnection0;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_TestNamedPipeRead1;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_TestNamedPipeWrite2;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_QueryResultSetAsXML3;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_QueryResultSetToPipe4;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_GetLastExceptionMessage5;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_GetLastExceptionStackTrace6;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_GetLastErrorCode7;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_main8;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_QueryDebugResultSetToPipe9;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_writeQvxResultSetToPipe10;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n__ctorMojoHiveDriver11;
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("()V")]
        public MojoHiveDriver() : 
                base(((global::net.sf.jni4net.jni.JNIEnv)(null))) {
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.ThreadEnv;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
            @__env.NewObject(global::mojohive.MojoHiveDriver.staticClass, global::mojohive.MojoHiveDriver.j4n__ctorMojoHiveDriver11, this);
            }
        }
        
        protected MojoHiveDriver(global::net.sf.jni4net.jni.JNIEnv @__env) : 
                base(@__env) {
        }
        
        public static global::java.lang.Class _class {
            get {
                return global::mojohive.MojoHiveDriver.staticClass;
            }
        }
        
        private static void InitJNI(global::net.sf.jni4net.jni.JNIEnv @__env, java.lang.Class @__class) {
            global::mojohive.MojoHiveDriver.staticClass = @__class;
            global::mojohive.MojoHiveDriver.j4n_TestConnection0 = @__env.GetMethodID(global::mojohive.MojoHiveDriver.staticClass, "TestConnection", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/la" +
                    "ng/String;)I");
            global::mojohive.MojoHiveDriver.j4n_TestNamedPipeRead1 = @__env.GetMethodID(global::mojohive.MojoHiveDriver.staticClass, "TestNamedPipeRead", "(Ljava/lang/String;)Ljava/lang/String;");
            global::mojohive.MojoHiveDriver.j4n_TestNamedPipeWrite2 = @__env.GetMethodID(global::mojohive.MojoHiveDriver.staticClass, "TestNamedPipeWrite", "(Ljava/lang/String;Ljava/lang/String;)I");
            global::mojohive.MojoHiveDriver.j4n_QueryResultSetAsXML3 = @__env.GetMethodID(global::mojohive.MojoHiveDriver.staticClass, "QueryResultSetAsXML", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/la" +
                    "ng/String;Ljava/lang/String;)Ljava/lang/String;");
            global::mojohive.MojoHiveDriver.j4n_QueryResultSetToPipe4 = @__env.GetMethodID(global::mojohive.MojoHiveDriver.staticClass, "QueryResultSetToPipe", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/la" +
                    "ng/String;Ljava/lang/String;Ljava/lang/String;)I");
            global::mojohive.MojoHiveDriver.j4n_GetLastExceptionMessage5 = @__env.GetMethodID(global::mojohive.MojoHiveDriver.staticClass, "GetLastExceptionMessage", "()Ljava/lang/String;");
            global::mojohive.MojoHiveDriver.j4n_GetLastExceptionStackTrace6 = @__env.GetMethodID(global::mojohive.MojoHiveDriver.staticClass, "GetLastExceptionStackTrace", "()Ljava/lang/String;");
            global::mojohive.MojoHiveDriver.j4n_GetLastErrorCode7 = @__env.GetMethodID(global::mojohive.MojoHiveDriver.staticClass, "GetLastErrorCode", "()I");
            global::mojohive.MojoHiveDriver.j4n_main8 = @__env.GetStaticMethodID(global::mojohive.MojoHiveDriver.staticClass, "main", "([Ljava/lang/String;)V");
            global::mojohive.MojoHiveDriver.j4n_QueryDebugResultSetToPipe9 = @__env.GetMethodID(global::mojohive.MojoHiveDriver.staticClass, "QueryDebugResultSetToPipe", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/la" +
                    "ng/String;Ljava/lang/String;Ljava/lang/String;)I");
            global::mojohive.MojoHiveDriver.j4n_writeQvxResultSetToPipe10 = @__env.GetStaticMethodID(global::mojohive.MojoHiveDriver.staticClass, "writeQvxResultSetToPipe", "(Ljava/sql/ResultSet;Ljava/lang/String;)I");
            global::mojohive.MojoHiveDriver.j4n__ctorMojoHiveDriver11 = @__env.GetMethodID(global::mojohive.MojoHiveDriver.staticClass, "<init>", "()V");
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/la" +
            "ng/String;)I")]
        public virtual int TestConnection(global::java.lang.String par0, global::java.lang.String par1, global::java.lang.String par2, global::java.lang.String par3, global::java.lang.String par4) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 20)){
            return ((int)(@__env.CallIntMethod(this, global::mojohive.MojoHiveDriver.j4n_TestConnection0, global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par0), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par1), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par2), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par3), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par4))));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Ljava/lang/String;)Ljava/lang/String;")]
        public virtual global::java.lang.String TestNamedPipeRead(global::java.lang.String par0) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 12)){
            return global::net.sf.jni4net.utils.Convertor.StrongJ2CpString(@__env, @__env.CallObjectMethodPtr(this, global::mojohive.MojoHiveDriver.j4n_TestNamedPipeRead1, global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par0)));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Ljava/lang/String;Ljava/lang/String;)I")]
        public virtual int TestNamedPipeWrite(global::java.lang.String par0, global::java.lang.String par1) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 14)){
            return ((int)(@__env.CallIntMethod(this, global::mojohive.MojoHiveDriver.j4n_TestNamedPipeWrite2, global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par0), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par1))));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/la" +
            "ng/String;Ljava/lang/String;)Ljava/lang/String;")]
        public virtual global::java.lang.String QueryResultSetAsXML(global::java.lang.String par0, global::java.lang.String par1, global::java.lang.String par2, global::java.lang.String par3, global::java.lang.String par4, global::java.lang.String par5) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 22)){
            return global::net.sf.jni4net.utils.Convertor.StrongJ2CpString(@__env, @__env.CallObjectMethodPtr(this, global::mojohive.MojoHiveDriver.j4n_QueryResultSetAsXML3, global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par0), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par1), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par2), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par3), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par4), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par5)));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/la" +
            "ng/String;Ljava/lang/String;Ljava/lang/String;)I")]
        public virtual int QueryResultSetToPipe(global::java.lang.String par0, global::java.lang.String par1, global::java.lang.String par2, global::java.lang.String par3, global::java.lang.String par4, global::java.lang.String par5, global::java.lang.String par6) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 24)){
            return ((int)(@__env.CallIntMethod(this, global::mojohive.MojoHiveDriver.j4n_QueryResultSetToPipe4, global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par0), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par1), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par2), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par3), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par4), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par5), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par6))));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("()Ljava/lang/String;")]
        public virtual global::java.lang.String GetLastExceptionMessage() {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
            return global::net.sf.jni4net.utils.Convertor.StrongJ2CpString(@__env, @__env.CallObjectMethodPtr(this, global::mojohive.MojoHiveDriver.j4n_GetLastExceptionMessage5));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("()Ljava/lang/String;")]
        public virtual global::java.lang.String GetLastExceptionStackTrace() {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
            return global::net.sf.jni4net.utils.Convertor.StrongJ2CpString(@__env, @__env.CallObjectMethodPtr(this, global::mojohive.MojoHiveDriver.j4n_GetLastExceptionStackTrace6));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("()I")]
        public virtual int GetLastErrorCode() {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
            return ((int)(@__env.CallIntMethod(this, global::mojohive.MojoHiveDriver.j4n_GetLastErrorCode7)));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("([Ljava/lang/String;)V")]
        public static void main(global::java.lang.String[] par0) {
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.ThreadEnv;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 12)){
            @__env.CallStaticVoidMethod(global::mojohive.MojoHiveDriver.staticClass, global::mojohive.MojoHiveDriver.j4n_main8, global::net.sf.jni4net.utils.Convertor.ParArrayStrongCp2J(@__env, par0));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/la" +
            "ng/String;Ljava/lang/String;Ljava/lang/String;)I")]
        public virtual int QueryDebugResultSetToPipe(global::java.lang.String par0, global::java.lang.String par1, global::java.lang.String par2, global::java.lang.String par3, global::java.lang.String par4, global::java.lang.String par5, global::java.lang.String par6) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 24)){
            return ((int)(@__env.CallIntMethod(this, global::mojohive.MojoHiveDriver.j4n_QueryDebugResultSetToPipe9, global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par0), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par1), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par2), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par3), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par4), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par5), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par6))));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Ljava/sql/ResultSet;Ljava/lang/String;)I")]
        public static int writeQvxResultSetToPipe(global::java.lang.Object par0, global::java.lang.String par1) {
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.ThreadEnv;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 14)){
            return ((int)(@__env.CallStaticIntMethod(global::mojohive.MojoHiveDriver.staticClass, global::mojohive.MojoHiveDriver.j4n_writeQvxResultSetToPipe10, global::net.sf.jni4net.utils.Convertor.ParFullC2J<global::java.lang.Object>(@__env, par0), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par1))));
            }
        }
        
        new internal sealed class ContructionHelper : global::net.sf.jni4net.utils.IConstructionHelper {
            
            public global::net.sf.jni4net.jni.IJvmProxy CreateProxy(global::net.sf.jni4net.jni.JNIEnv @__env) {
                return new global::mojohive.MojoHiveDriver(@__env);
            }
        }
    }
    #endregion
}
