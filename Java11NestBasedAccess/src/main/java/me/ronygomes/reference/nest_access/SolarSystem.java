package me.ronygomes.reference.nest_access;

public class SolarSystem {

    public String solarSystemPublic() {
        return "solarSystemPublic";
    }

    public String solarSystemPrivateNoCall() {
        return "solarSystemPrivateNoCall";
    }

    private String solarSystemPrivateForUranus() {
        return "solarSystemPrivateForUranus";
    }

    private String solarSystemPrivateForAsia() {
        return "solarSystemPrivateForAsia";
    }

    class Earth {

        private String earthPrivateForAsia() {
            return "earthPrivateForAsia";
        }

        class Asia {
            public String asiaPublicCallsSolarSystemPrivateForAsia() {
                return "asiaPublicCallsSolarSystemPrivateForAsia -> " + solarSystemPrivateForAsia();
            }

            public String asiaPublicCallsEarthPrivateForAsia() {
                return "asiaPublicCallsEarthPrivateForAsia -> " + earthPrivateForAsia();
            }
        }

        class Europe {
            public String europePublicCallsSolarSystemPublic() {
                return "europePublicCallsSolarSystemPublic -> " + solarSystemPublic();
            }
        }
    }

    class Uranus {
        public String uranusPublicCallsSolarSystemPrivateForUranus() {
            return "uranusPublicCallsSolarSystemPrivateForUranus -> " + solarSystemPrivateForUranus();
        }
    }

    class Saturn {
        public String saturnPublic() {
            return "saturnPublic";
        }
    }
}
