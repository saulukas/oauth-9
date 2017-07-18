package oauth9.utils.mustache;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import oauth9.utils.ClasspathUtils;

public class MustacheUtils {

    public static MustacheTemplate templateNextTo(Class<?> klass) {
        String fileName = klass.getSimpleName() + ".html";
        String resourcePath = ClasspathUtils.resourcePathNextToClass(klass, fileName);
        String rawTemplate = ClasspathUtils.resourceToString(klass, resourcePath);
        Mustache.Compiler compiler = Mustache.compiler()
                .nullValue("")
                .emptyStringIsFalse(true)
                .standardsMode(true)
                .strictSections(true);
        Template compliledTemplate = compiler.compile(rawTemplate);
        return new MustacheTemplate(klass.getSimpleName(), rawTemplate, compliledTemplate);
    }
}
