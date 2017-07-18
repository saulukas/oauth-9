package oauth9.utils;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class StreamUtils {

    public static <T, R> List<R> mapToList(
            Collection<T> collection,
            Function<? super T,? extends R> mapper
    ) {
        return collection.stream().map(mapper).collect(toList());
    }

}
