//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by jni4net. See http://jni4net.sourceforge.net/ 
//     Runtime Version:4.0.30319.17929
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace mojohive.testmode {
    
    
    #region Component Designer generated code 
    [global::net.sf.jni4net.attributes.JavaClassAttribute()]
    public partial class EmbeddedH2Database : global::java.lang.Object {
        
        internal new static global::java.lang.Class staticClass;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_getInstance0;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_initIfNeeded1;
        
        internal static global::net.sf.jni4net.jni.FieldId j4n_H2_JDBC_DRIVER_CLASS2;
        
        protected EmbeddedH2Database(global::net.sf.jni4net.jni.JNIEnv @__env) : 
                base(@__env) {
        }
        
        public static global::java.lang.Class _class {
            get {
                return global::mojohive.testmode.EmbeddedH2Database.staticClass;
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("Ljava/lang/String;")]
        public static global::java.lang.String H2_JDBC_DRIVER_CLASS {
            get {
                global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.ThreadEnv;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
                return global::net.sf.jni4net.utils.Convertor.StrongJ2CpString(@__env, @__env.GetStaticObjectFieldPtr(global::mojohive.testmode.EmbeddedH2Database.staticClass, global::mojohive.testmode.EmbeddedH2Database.j4n_H2_JDBC_DRIVER_CLASS2));
            }
            }
        }
        
        private static void InitJNI(global::net.sf.jni4net.jni.JNIEnv @__env, java.lang.Class @__class) {
            global::mojohive.testmode.EmbeddedH2Database.staticClass = @__class;
            global::mojohive.testmode.EmbeddedH2Database.j4n_getInstance0 = @__env.GetStaticMethodID(global::mojohive.testmode.EmbeddedH2Database.staticClass, "getInstance", "()Lmojohive/testmode/EmbeddedH2Database;");
            global::mojohive.testmode.EmbeddedH2Database.j4n_initIfNeeded1 = @__env.GetStaticMethodID(global::mojohive.testmode.EmbeddedH2Database.staticClass, "initIfNeeded", "(Ljava/lang/String;)V");
            global::mojohive.testmode.EmbeddedH2Database.j4n_H2_JDBC_DRIVER_CLASS2 = @__env.GetStaticFieldID(global::mojohive.testmode.EmbeddedH2Database.staticClass, "H2_JDBC_DRIVER_CLASS", "Ljava/lang/String;");
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("()Lmojohive/testmode/EmbeddedH2Database;")]
        public static global::mojohive.testmode.EmbeddedH2Database getInstance() {
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.ThreadEnv;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
            return global::net.sf.jni4net.utils.Convertor.StrongJ2Cp<global::mojohive.testmode.EmbeddedH2Database>(@__env, @__env.CallStaticObjectMethodPtr(global::mojohive.testmode.EmbeddedH2Database.staticClass, global::mojohive.testmode.EmbeddedH2Database.j4n_getInstance0));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Ljava/lang/String;)V")]
        public static void initIfNeeded(global::java.lang.String par0) {
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.ThreadEnv;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 12)){
            @__env.CallStaticVoidMethod(global::mojohive.testmode.EmbeddedH2Database.staticClass, global::mojohive.testmode.EmbeddedH2Database.j4n_initIfNeeded1, global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par0));
            }
        }
        
        new internal sealed class ContructionHelper : global::net.sf.jni4net.utils.IConstructionHelper {
            
            public global::net.sf.jni4net.jni.IJvmProxy CreateProxy(global::net.sf.jni4net.jni.JNIEnv @__env) {
                return new global::mojohive.testmode.EmbeddedH2Database(@__env);
            }
        }
    }
    #endregion
}
