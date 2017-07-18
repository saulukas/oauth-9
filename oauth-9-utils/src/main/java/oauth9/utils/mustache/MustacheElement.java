package oauth9.utils.mustache;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import java.io.IOException;
import java.io.Writer;
import oauth9.utils.Dto;

/**
 * Poor man's Virtual DOM Element/Node - used to be popular thing in the last years. 
 */
public class MustacheElement extends Dto implements Mustache.Lambda {

    final public MustacheTemplate template;
    final public Object model;

    public MustacheElement(MustacheTemplate template, Object model) {
        this.template = template;
        this.model = model;
    }
    
    public String render() {
        return template.render(model);
    }

    public void renderTo(Writer writer) {
        template.renderTo(writer, model);
    }

    @Override
    public void execute(Template.Fragment frag, Writer out) throws IOException {
        renderTo(out);
    }
}
