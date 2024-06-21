package br.com.fiap.delivery.dto.route;


public class Constants {

    public static class TypePolyLine {
        public static final String HIGHT = "high_quality";
        public static final String DEFAULT = "overview";
        protected TypePolyLine() {
            throw new IllegalStateException("Utility class");
        }
    }

    public static class TypeRoute {
        public static final String OPTIMAL = "traffic_aware_optimal";
        public static final String DEFAULT = "traffic_unaware";

        public static final String UNSPECIFIED = "ROUTING_PREFERENCE_UNSPECIFIED";

        protected TypeRoute() {
            throw new IllegalStateException("Utility class");
        }
    }

    public static class Units {
        public static final String METRIC = "METRIC";
        public static final String MILE = "IMPERIAL";

        protected Units() {
            throw new IllegalStateException("Utility class");
        }
    }

    public static class Lang {
        public static final String PORTUGUESE = "pt-BR";
        public static final String ENGLISH = "en-US";
        protected Lang() {
            throw new IllegalStateException("Utility class");
        }
    }

    protected Constants() {
        throw new IllegalStateException("Utility class");
    }

}
