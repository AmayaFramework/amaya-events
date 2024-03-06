//package io.github.amayaframework.events;
//
//import com.github.romanqed.jfunc.Runnable1;
//
//final class RunnableHolder implements Runnable1<Object> {
//    private Runnable1<Object> body;
//
//    @SuppressWarnings("unchecked")
//    RunnableHolder(Runnable1<?> body) {
//        this.body = (Runnable1<Object>) body;
//    }
//
//    public Runnable1<Object> getBody() {
//        return body;
//    }
//
//    @SuppressWarnings("unchecked")
//    public void setBody(Runnable1<?> body) {
//        this.body = (Runnable1<Object>) body;
//    }
//
//    @Override
//    public void run(Object o) throws Throwable {
//        body.run(o);
//    }
//
//    @Override
//    public Runnable1<Object> andThen(Runnable1<Object> func) {
//        return body.andThen(func);
//    }
//}
