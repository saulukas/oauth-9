package oauth9.utils.mustache;

import java.util.concurrent.atomic.AtomicLong;
import static oauth9.utils.mustache.MustacheUtils.templateNextTo;

public class MustacheView {

    private static final AtomicLong LAST_DOM_ID = new AtomicLong();
    
    public static volatile boolean cacheTemplates = false;

    private volatile MustacheTemplate template = templateNextTo(this.getClass());

    protected final Object NULL_MODEL = null;

    public MustacheTemplate template() {
        if (!cacheTemplates) {
            template = templateNextTo(this.getClass());
        }
        return template;
    }

    public MustacheElement elementWith(Object model) {
        return new MustacheElement(template(), model);
    }

    public String render(Object model) {
        return elementWith(model).render();
    }
    
    public static String nextDomId() {
        return "di" + LAST_DOM_ID.incrementAndGet();
    }
}
