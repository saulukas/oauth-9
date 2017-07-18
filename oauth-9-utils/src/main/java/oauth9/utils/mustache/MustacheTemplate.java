package oauth9.utils.mustache;

import com.samskivert.mustache.Template;
import java.io.Writer;

public class MustacheTemplate {

    public final String name;
    public final String raw;
    public final Template compiled;

    public MustacheTemplate(String name, String raw, Template compiled) {
        this.name = name;
        this.raw = raw;
        this.compiled = compiled;
    }

    public String render(Object model) {
        try {
            return compiled.execute(model);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to render template " + name + ": " + ex, ex);
        }
    }

    public void renderTo(Writer writer, Object model) {
        try {
            compiled.execute(model, writer);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to render template " + name + ": " + ex, ex);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + name;
    }
}
