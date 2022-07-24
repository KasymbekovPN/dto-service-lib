package kpn.lib.ripper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Function;

import kpn.lib.domain.Domain;

public class DefaultRipper<D extends Domain<?>> implements Ripper<D>{
    private static final String DEFAULT_GETTING_RESULT = "-";

    private final Map<String, Function<RipperArg<D>, Optional<String>>> getters;

    public static <D extends Domain<?>> Buider<D> buider(){
        return new Buider<>();
    }

    private DefaultRipper(Map<String, Function<RipperArg<D>, Optional<String>>> getters) {
        this.getters = getters;
    }

    @Override
    public String run(RipperArg<D> arg) {
        Queue<String> path = arg.getPath();
        String key = path.poll();
        return key != null && getters.containsKey(key)
            ? getters.get(key).apply(arg).orElse(DEFAULT_GETTING_RESULT)
            : DEFAULT_GETTING_RESULT;
    }

    public static class Buider<D extends Domain<?>>{
        private Map<String, Function<RipperArg<D>, Optional<String>>> getters = new HashMap<>();

        public Buider<D> getter(String key, Function<RipperArg<D>, Optional<String>> getter){
            getters.put(key, getter);
            return this;
        }

        public DefaultRipper<D> build(){
            return new DefaultRipper<>(getters);
        }
    }
}
