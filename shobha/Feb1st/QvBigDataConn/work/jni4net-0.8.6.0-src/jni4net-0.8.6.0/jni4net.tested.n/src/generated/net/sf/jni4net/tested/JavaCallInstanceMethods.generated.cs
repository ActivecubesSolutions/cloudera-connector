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
    [global::net.sf.jni4net.attributes.JavaClassAttribute()]
    public partial class JavaCallInstanceMethods : global::java.lang.Object {
        
        internal new static global::java.lang.Class staticClass;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_stringMethod0;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_objectMethod1;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_testObjectMethod2;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_testStringMethod3;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_integerMethod4;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_testIntegerMethod5;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_classLoaderMethod6;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_testClassLoaderMethod7;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_objectArray8;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_charArray9;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_intArray10;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_integerArray11;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_stringArray12;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_booleanArray13;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_buffer14;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n_stringList15;
        
        internal static global::net.sf.jni4net.jni.MethodId j4n__ctorJavaCallInstanceMethods16;
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("()V")]
        public JavaCallInstanceMethods() : 
                base(((global::net.sf.jni4net.jni.JNIEnv)(null))) {
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.ThreadEnv;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
            @__env.NewObject(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n__ctorJavaCallInstanceMethods16, this);
            }
        }
        
        protected JavaCallInstanceMethods(global::net.sf.jni4net.jni.JNIEnv @__env) : 
                base(@__env) {
        }
        
        public new static global::java.lang.Class _class {
            get {
                return global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass;
            }
        }
        
        private static void InitJNI(global::net.sf.jni4net.jni.JNIEnv @__env, java.lang.Class @__class) {
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass = @__class;
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_stringMethod0 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "stringMethod", "()Ljava/lang/String;");
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_objectMethod1 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "objectMethod", "()Ljava/lang/Object;");
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_testObjectMethod2 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "testObjectMethod", "(Ljava/lang/Object;)Z");
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_testStringMethod3 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "testStringMethod", "(Ljava/lang/String;)Z");
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_integerMethod4 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "integerMethod", "()Ljava/lang/Integer;");
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_testIntegerMethod5 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "testIntegerMethod", "(Ljava/lang/Integer;)Z");
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_classLoaderMethod6 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "classLoaderMethod", "()Ljava/lang/ClassLoader;");
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_testClassLoaderMethod7 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "testClassLoaderMethod", "(Ljava/lang/ClassLoader;)Z");
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_objectArray8 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "objectArray", "([Ljava/lang/Object;)[Ljava/lang/Object;");
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_charArray9 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "charArray", "([C)[C");
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_intArray10 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "intArray", "([I)[I");
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_integerArray11 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "integerArray", "([Ljava/lang/Integer;)[Ljava/lang/Integer;");
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_stringArray12 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "stringArray", "([Ljava/lang/String;)[Ljava/lang/String;");
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_booleanArray13 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "booleanArray", "([Z)[Z");
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_buffer14 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "buffer", "(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;");
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_stringList15 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "stringList", "()Ljava/util/List;");
            global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n__ctorJavaCallInstanceMethods16 = @__env.GetMethodID(global::net.sf.jni4net.tested.JavaCallInstanceMethods.staticClass, "<init>", "()V");
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("()Ljava/lang/String;")]
        public virtual global::java.lang.String stringMethod() {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
            return global::net.sf.jni4net.utils.Convertor.StrongJ2CpString(@__env, @__env.CallObjectMethodPtr(this, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_stringMethod0));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("()Ljava/lang/Object;")]
        public virtual global::java.lang.Object objectMethod() {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
            return global::net.sf.jni4net.utils.Convertor.FullJ2C<global::java.lang.Object>(@__env, @__env.CallObjectMethodPtr(this, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_objectMethod1));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Ljava/lang/Object;)Z")]
        public virtual bool testObjectMethod(global::java.lang.Object par0) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 12)){
            return ((bool)(@__env.CallBooleanMethod(this, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_testObjectMethod2, global::net.sf.jni4net.utils.Convertor.ParFullC2J<global::java.lang.Object>(@__env, par0))));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Ljava/lang/String;)Z")]
        public virtual bool testStringMethod(global::java.lang.String par0) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 12)){
            return ((bool)(@__env.CallBooleanMethod(this, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_testStringMethod3, global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par0))));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("()Ljava/lang/Integer;")]
        public virtual global::java.lang.Integer integerMethod() {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
            return global::net.sf.jni4net.utils.Convertor.StrongJ2Cp<global::java.lang.Integer>(@__env, @__env.CallObjectMethodPtr(this, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_integerMethod4));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Ljava/lang/Integer;)Z")]
        public virtual bool testIntegerMethod(global::java.lang.Integer par0) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 12)){
            return ((bool)(@__env.CallBooleanMethod(this, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_testIntegerMethod5, global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par0))));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("()Ljava/lang/ClassLoader;")]
        public virtual global::java.lang.ClassLoader classLoaderMethod() {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
            return global::net.sf.jni4net.utils.Convertor.StrongJ2Cp<global::java.lang.ClassLoader>(@__env, @__env.CallObjectMethodPtr(this, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_classLoaderMethod6));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Ljava/lang/ClassLoader;)Z")]
        public virtual bool testClassLoaderMethod(global::java.lang.ClassLoader par0) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 12)){
            return ((bool)(@__env.CallBooleanMethod(this, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_testClassLoaderMethod7, global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par0))));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("([Ljava/lang/Object;)[Ljava/lang/Object;")]
        public virtual java.lang.Object[] objectArray(java.lang.Object[] par0) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 12)){
            return global::net.sf.jni4net.utils.Convertor.ArrayFullJ2C<java.lang.Object[], global::java.lang.Object>(@__env, @__env.CallObjectMethodPtr(this, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_objectArray8, global::net.sf.jni4net.utils.Convertor.ParArrayFullC2J<java.lang.Object[], global::java.lang.Object>(@__env, par0)));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("([C)[C")]
        public virtual char[] charArray(char[] par0) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 12)){
            return global::net.sf.jni4net.utils.Convertor.ArrayPrimJ2Cchar(@__env, @__env.CallObjectMethodPtr(this, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_charArray9, global::net.sf.jni4net.utils.Convertor.ParArrayPrimC2J(@__env, par0)));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("([I)[I")]
        public virtual int[] intArray(int[] par0) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 12)){
            return global::net.sf.jni4net.utils.Convertor.ArrayPrimJ2Cint(@__env, @__env.CallObjectMethodPtr(this, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_intArray10, global::net.sf.jni4net.utils.Convertor.ParArrayPrimC2J(@__env, par0)));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("([Ljava/lang/Integer;)[Ljava/lang/Integer;")]
        public virtual java.lang.Integer[] integerArray(java.lang.Integer[] par0) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 12)){
            return global::net.sf.jni4net.utils.Convertor.ArrayStrongJ2Cp<java.lang.Integer[], global::java.lang.Integer>(@__env, @__env.CallObjectMethodPtr(this, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_integerArray11, global::net.sf.jni4net.utils.Convertor.ParArrayStrongCp2J(@__env, par0)));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("([Ljava/lang/String;)[Ljava/lang/String;")]
        public virtual java.lang.String[] stringArray(java.lang.String[] par0) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 12)){
            return global::net.sf.jni4net.utils.Convertor.ArrayStrongJ2CpString(@__env, @__env.CallObjectMethodPtr(this, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_stringArray12, global::net.sf.jni4net.utils.Convertor.ParArrayStrongCp2J(@__env, par0)));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("([Z)[Z")]
        public virtual bool[] booleanArray(bool[] par0) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 12)){
            return global::net.sf.jni4net.utils.Convertor.ArrayPrimJ2Cboolean(@__env, @__env.CallObjectMethodPtr(this, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_booleanArray13, global::net.sf.jni4net.utils.Convertor.ParArrayPrimC2J(@__env, par0)));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;")]
        public virtual global::java.nio.ByteBuffer buffer(global::java.nio.ByteBuffer par0) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 12)){
            return global::net.sf.jni4net.utils.Convertor.StrongJ2Cp<global::java.nio.ByteBuffer>(@__env, @__env.CallObjectMethodPtr(this, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_buffer14, global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par0)));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("()Ljava/util/List;")]
        public virtual global::java.util.List stringList() {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
            return global::net.sf.jni4net.utils.Convertor.FullJ2C<global::java.util.List>(@__env, @__env.CallObjectMethodPtr(this, global::net.sf.jni4net.tested.JavaCallInstanceMethods.j4n_stringList15));
            }
        }
        
        new internal sealed class ContructionHelper : global::net.sf.jni4net.utils.IConstructionHelper {
            
            public global::net.sf.jni4net.jni.IJvmProxy CreateProxy(global::net.sf.jni4net.jni.JNIEnv @__env) {
                return new global::net.sf.jni4net.tested.JavaCallInstanceMethods(@__env);
            }
        }
    }
    #endregion
}
