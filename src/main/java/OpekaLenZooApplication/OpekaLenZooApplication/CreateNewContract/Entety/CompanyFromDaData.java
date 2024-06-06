package OpekaLenZooApplication.OpekaLenZooApplication.CreateNewContract.Entety;

import java.util.UUID;

public class CompanyFromDaData {
    private Suggestion[] suggestions;

    public Suggestion[] getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(Suggestion[] value) {
        this.suggestions = value;
    }


// Suggestion.java

    public class Suggestion {
        private String value;
        private SuggestionData data;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public SuggestionData getData() {
            return data;
        }

        public void setData(SuggestionData value) {
            this.data = value;
        }
    }

// SuggestionData.java

    public class SuggestionData {
        private String kpp;
        private Management management;
        private String type;
        private Name name;
        private String inn;
        private String ogrn;
        private String okpo;
        private Address address;

        private State state;

        public String getKpp() {
            return kpp;
        }

        public void setKpp(String value) {
            this.kpp = value;
        }

        public Management getManagement() {
            return management;
        }

        public void setManagement(Management value) {
            this.management = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String value) {
            this.type = value;
        }

        public Name getName() {
            return name;
        }

        public void setName(Name value) {
            this.name = value;
        }

        public String getInn() {
            return inn;
        }

        public void setInn(String value) {
            this.inn = value;
        }

        public String getOgrn() {
            return ogrn;
        }

        public void setOgrn(String value) {
            this.ogrn = value;
        }

        public String getOkpo() {
            return okpo;
        }

        public void setOkpo(String value) {
            this.okpo = value;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address value) {
            this.address = value;
        }

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }
    }

    public class Address {
        private String value;
        private String unrestrictedValue;
        private Object invalidity;
        private AddressData data;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getUnrestrictedValue() {
            return unrestrictedValue;
        }

        public void setUnrestrictedValue(String value) {
            this.unrestrictedValue = value;
        }

        public Object getInvalidity() {
            return invalidity;
        }

        public void setInvalidity(Object value) {
            this.invalidity = value;
        }

        public AddressData getData() {
            return data;
        }

        public void setData(AddressData value) {
            this.data = value;
        }
    }

// AddressData.java

    public class AddressData {
        private String postalCode;
        private String country;
        private String countryISOCode;
        private String federalDistrict;
        private UUID regionFiasID;
        private String regionKladrID;
        private String regionISOCode;
        private String regionWithType;
        private String regionType;
        private String regionTypeFull;
        private String region;
        private Object areaFiasID;
        private Object areaKladrID;
        private Object areaWithType;
        private Object areaType;
        private Object areaTypeFull;
        private Object area;
        private UUID cityFiasID;
        private String cityKladrID;
        private String cityWithType;
        private String cityType;
        private String cityTypeFull;
        private String city;
        private Object cityArea;
        private Object cityDistrictFiasID;
        private Object cityDistrictKladrID;
        private String cityDistrictWithType;
        private String cityDistrictType;
        private String cityDistrictTypeFull;
        private String cityDistrict;
        private Object settlementFiasID;
        private Object settlementKladrID;
        private Object settlementWithType;
        private Object settlementType;
        private Object settlementTypeFull;
        private Object settlement;
        private UUID streetFiasID;
        private String streetKladrID;
        private String streetWithType;
        private String streetType;
        private String streetTypeFull;
        private String street;
        private Object steadFiasID;
        private Object steadCadnum;
        private Object steadType;
        private Object steadTypeFull;
        private Object stead;
        private UUID houseFiasID;
        private String houseKladrID;
        private String houseCadnum;
        private String houseType;
        private String houseTypeFull;
        private String house;
        private String blockType;
        private String blockTypeFull;
        private String block;
        private Object entrance;
        private Object floor;
        private Object flatFiasID;
        private Object flatCadnum;
        private String flatType;
        private String flatTypeFull;
        private String flat;
        private Object flatArea;
        private String squareMeterPrice;
        private Object flatPrice;
        private Object roomFiasID;
        private Object roomCadnum;
        private Object roomType;
        private Object roomTypeFull;
        private Object room;
        private Object postalBox;
        private UUID fiasID;
        private String fiasCode;
        private String fiasLevel;
        private String fiasActualityState;
        private String kladrID;
        private String geonameID;
        private String capitalMarker;
        private String okato;
        private String oktmo;
        private String taxOffice;
        private String taxOfficeLegal;
        private String timezone;
        private String geoLat;
        private String geoLon;
        private String beltwayHit;
        private Object beltwayDistance;
        private Metro[] metro;
        private Object divisions;
        private String qcGeo;
        private Object qcComplete;
        private Object qcHouse;
        private Object historyValues;
        private Object unparsedParts;
        private String source;
        private String qc;

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String value) {
            this.postalCode = value;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String value) {
            this.country = value;
        }

        public String getCountryISOCode() {
            return countryISOCode;
        }

        public void setCountryISOCode(String value) {
            this.countryISOCode = value;
        }

        public String getFederalDistrict() {
            return federalDistrict;
        }

        public void setFederalDistrict(String value) {
            this.federalDistrict = value;
        }

        public UUID getRegionFiasID() {
            return regionFiasID;
        }

        public void setRegionFiasID(UUID value) {
            this.regionFiasID = value;
        }

        public String getRegionKladrID() {
            return regionKladrID;
        }

        public void setRegionKladrID(String value) {
            this.regionKladrID = value;
        }

        public String getRegionISOCode() {
            return regionISOCode;
        }

        public void setRegionISOCode(String value) {
            this.regionISOCode = value;
        }

        public String getRegionWithType() {
            return regionWithType;
        }

        public void setRegionWithType(String value) {
            this.regionWithType = value;
        }

        public String getRegionType() {
            return regionType;
        }

        public void setRegionType(String value) {
            this.regionType = value;
        }

        public String getRegionTypeFull() {
            return regionTypeFull;
        }

        public void setRegionTypeFull(String value) {
            this.regionTypeFull = value;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String value) {
            this.region = value;
        }

        public Object getAreaFiasID() {
            return areaFiasID;
        }

        public void setAreaFiasID(Object value) {
            this.areaFiasID = value;
        }

        public Object getAreaKladrID() {
            return areaKladrID;
        }

        public void setAreaKladrID(Object value) {
            this.areaKladrID = value;
        }

        public Object getAreaWithType() {
            return areaWithType;
        }

        public void setAreaWithType(Object value) {
            this.areaWithType = value;
        }

        public Object getAreaType() {
            return areaType;
        }

        public void setAreaType(Object value) {
            this.areaType = value;
        }

        public Object getAreaTypeFull() {
            return areaTypeFull;
        }

        public void setAreaTypeFull(Object value) {
            this.areaTypeFull = value;
        }

        public Object getArea() {
            return area;
        }

        public void setArea(Object value) {
            this.area = value;
        }

        public UUID getCityFiasID() {
            return cityFiasID;
        }

        public void setCityFiasID(UUID value) {
            this.cityFiasID = value;
        }

        public String getCityKladrID() {
            return cityKladrID;
        }

        public void setCityKladrID(String value) {
            this.cityKladrID = value;
        }

        public String getCityWithType() {
            return cityWithType;
        }

        public void setCityWithType(String value) {
            this.cityWithType = value;
        }

        public String getCityType() {
            return cityType;
        }

        public void setCityType(String value) {
            this.cityType = value;
        }

        public String getCityTypeFull() {
            return cityTypeFull;
        }

        public void setCityTypeFull(String value) {
            this.cityTypeFull = value;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String value) {
            this.city = value;
        }

        public Object getCityArea() {
            return cityArea;
        }

        public void setCityArea(Object value) {
            this.cityArea = value;
        }

        public Object getCityDistrictFiasID() {
            return cityDistrictFiasID;
        }

        public void setCityDistrictFiasID(Object value) {
            this.cityDistrictFiasID = value;
        }

        public Object getCityDistrictKladrID() {
            return cityDistrictKladrID;
        }

        public void setCityDistrictKladrID(Object value) {
            this.cityDistrictKladrID = value;
        }

        public String getCityDistrictWithType() {
            return cityDistrictWithType;
        }

        public void setCityDistrictWithType(String value) {
            this.cityDistrictWithType = value;
        }

        public String getCityDistrictType() {
            return cityDistrictType;
        }

        public void setCityDistrictType(String value) {
            this.cityDistrictType = value;
        }

        public String getCityDistrictTypeFull() {
            return cityDistrictTypeFull;
        }

        public void setCityDistrictTypeFull(String value) {
            this.cityDistrictTypeFull = value;
        }

        public String getCityDistrict() {
            return cityDistrict;
        }

        public void setCityDistrict(String value) {
            this.cityDistrict = value;
        }

        public Object getSettlementFiasID() {
            return settlementFiasID;
        }

        public void setSettlementFiasID(Object value) {
            this.settlementFiasID = value;
        }

        public Object getSettlementKladrID() {
            return settlementKladrID;
        }

        public void setSettlementKladrID(Object value) {
            this.settlementKladrID = value;
        }

        public Object getSettlementWithType() {
            return settlementWithType;
        }

        public void setSettlementWithType(Object value) {
            this.settlementWithType = value;
        }

        public Object getSettlementType() {
            return settlementType;
        }

        public void setSettlementType(Object value) {
            this.settlementType = value;
        }

        public Object getSettlementTypeFull() {
            return settlementTypeFull;
        }

        public void setSettlementTypeFull(Object value) {
            this.settlementTypeFull = value;
        }

        public Object getSettlement() {
            return settlement;
        }

        public void setSettlement(Object value) {
            this.settlement = value;
        }

        public UUID getStreetFiasID() {
            return streetFiasID;
        }

        public void setStreetFiasID(UUID value) {
            this.streetFiasID = value;
        }

        public String getStreetKladrID() {
            return streetKladrID;
        }

        public void setStreetKladrID(String value) {
            this.streetKladrID = value;
        }

        public String getStreetWithType() {
            return streetWithType;
        }

        public void setStreetWithType(String value) {
            this.streetWithType = value;
        }

        public String getStreetType() {
            return streetType;
        }

        public void setStreetType(String value) {
            this.streetType = value;
        }

        public String getStreetTypeFull() {
            return streetTypeFull;
        }

        public void setStreetTypeFull(String value) {
            this.streetTypeFull = value;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String value) {
            this.street = value;
        }

        public Object getSteadFiasID() {
            return steadFiasID;
        }

        public void setSteadFiasID(Object value) {
            this.steadFiasID = value;
        }

        public Object getSteadCadnum() {
            return steadCadnum;
        }

        public void setSteadCadnum(Object value) {
            this.steadCadnum = value;
        }

        public Object getSteadType() {
            return steadType;
        }

        public void setSteadType(Object value) {
            this.steadType = value;
        }

        public Object getSteadTypeFull() {
            return steadTypeFull;
        }

        public void setSteadTypeFull(Object value) {
            this.steadTypeFull = value;
        }

        public Object getStead() {
            return stead;
        }

        public void setStead(Object value) {
            this.stead = value;
        }

        public UUID getHouseFiasID() {
            return houseFiasID;
        }

        public void setHouseFiasID(UUID value) {
            this.houseFiasID = value;
        }

        public String getHouseKladrID() {
            return houseKladrID;
        }

        public void setHouseKladrID(String value) {
            this.houseKladrID = value;
        }

        public String getHouseCadnum() {
            return houseCadnum;
        }

        public void setHouseCadnum(String value) {
            this.houseCadnum = value;
        }

        public String getHouseType() {
            return houseType;
        }

        public void setHouseType(String value) {
            this.houseType = value;
        }

        public String getHouseTypeFull() {
            return houseTypeFull;
        }

        public void setHouseTypeFull(String value) {
            this.houseTypeFull = value;
        }

        public String getHouse() {
            return house;
        }

        public void setHouse(String value) {
            this.house = value;
        }

        public String getBlockType() {
            return blockType;
        }

        public void setBlockType(String value) {
            this.blockType = value;
        }

        public String getBlockTypeFull() {
            return blockTypeFull;
        }

        public void setBlockTypeFull(String value) {
            this.blockTypeFull = value;
        }

        public String getBlock() {
            return block;
        }

        public void setBlock(String value) {
            this.block = value;
        }

        public Object getEntrance() {
            return entrance;
        }

        public void setEntrance(Object value) {
            this.entrance = value;
        }

        public Object getFloor() {
            return floor;
        }

        public void setFloor(Object value) {
            this.floor = value;
        }

        public Object getFlatFiasID() {
            return flatFiasID;
        }

        public void setFlatFiasID(Object value) {
            this.flatFiasID = value;
        }

        public Object getFlatCadnum() {
            return flatCadnum;
        }

        public void setFlatCadnum(Object value) {
            this.flatCadnum = value;
        }

        public String getFlatType() {
            return flatType;
        }

        public void setFlatType(String value) {
            this.flatType = value;
        }

        public String getFlatTypeFull() {
            return flatTypeFull;
        }

        public void setFlatTypeFull(String value) {
            this.flatTypeFull = value;
        }

        public String getFlat() {
            return flat;
        }

        public void setFlat(String value) {
            this.flat = value;
        }

        public Object getFlatArea() {
            return flatArea;
        }

        public void setFlatArea(Object value) {
            this.flatArea = value;
        }

        public String getSquareMeterPrice() {
            return squareMeterPrice;
        }

        public void setSquareMeterPrice(String value) {
            this.squareMeterPrice = value;
        }

        public Object getFlatPrice() {
            return flatPrice;
        }

        public void setFlatPrice(Object value) {
            this.flatPrice = value;
        }

        public Object getRoomFiasID() {
            return roomFiasID;
        }

        public void setRoomFiasID(Object value) {
            this.roomFiasID = value;
        }

        public Object getRoomCadnum() {
            return roomCadnum;
        }

        public void setRoomCadnum(Object value) {
            this.roomCadnum = value;
        }

        public Object getRoomType() {
            return roomType;
        }

        public void setRoomType(Object value) {
            this.roomType = value;
        }

        public Object getRoomTypeFull() {
            return roomTypeFull;
        }

        public void setRoomTypeFull(Object value) {
            this.roomTypeFull = value;
        }

        public Object getRoom() {
            return room;
        }

        public void setRoom(Object value) {
            this.room = value;
        }

        public Object getPostalBox() {
            return postalBox;
        }

        public void setPostalBox(Object value) {
            this.postalBox = value;
        }

        public UUID getFiasID() {
            return fiasID;
        }

        public void setFiasID(UUID value) {
            this.fiasID = value;
        }

        public String getFiasCode() {
            return fiasCode;
        }

        public void setFiasCode(String value) {
            this.fiasCode = value;
        }

        public String getFiasLevel() {
            return fiasLevel;
        }

        public void setFiasLevel(String value) {
            this.fiasLevel = value;
        }

        public String getFiasActualityState() {
            return fiasActualityState;
        }

        public void setFiasActualityState(String value) {
            this.fiasActualityState = value;
        }

        public String getKladrID() {
            return kladrID;
        }

        public void setKladrID(String value) {
            this.kladrID = value;
        }

        public String getGeonameID() {
            return geonameID;
        }

        public void setGeonameID(String value) {
            this.geonameID = value;
        }

        public String getCapitalMarker() {
            return capitalMarker;
        }

        public void setCapitalMarker(String value) {
            this.capitalMarker = value;
        }

        public String getOkato() {
            return okato;
        }

        public void setOkato(String value) {
            this.okato = value;
        }

        public String getOktmo() {
            return oktmo;
        }

        public void setOktmo(String value) {
            this.oktmo = value;
        }

        public String getTaxOffice() {
            return taxOffice;
        }

        public void setTaxOffice(String value) {
            this.taxOffice = value;
        }

        public String getTaxOfficeLegal() {
            return taxOfficeLegal;
        }

        public void setTaxOfficeLegal(String value) {
            this.taxOfficeLegal = value;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String value) {
            this.timezone = value;
        }

        public String getGeoLat() {
            return geoLat;
        }

        public void setGeoLat(String value) {
            this.geoLat = value;
        }

        public String getGeoLon() {
            return geoLon;
        }

        public void setGeoLon(String value) {
            this.geoLon = value;
        }

        public String getBeltwayHit() {
            return beltwayHit;
        }

        public void setBeltwayHit(String value) {
            this.beltwayHit = value;
        }

        public Object getBeltwayDistance() {
            return beltwayDistance;
        }

        public void setBeltwayDistance(Object value) {
            this.beltwayDistance = value;
        }

        public Metro[] getMetro() {
            return metro;
        }

        public void setMetro(Metro[] value) {
            this.metro = value;
        }

        public Object getDivisions() {
            return divisions;
        }

        public void setDivisions(Object value) {
            this.divisions = value;
        }

        public String getQcGeo() {
            return qcGeo;
        }

        public void setQcGeo(String value) {
            this.qcGeo = value;
        }

        public Object getQcComplete() {
            return qcComplete;
        }

        public void setQcComplete(Object value) {
            this.qcComplete = value;
        }

        public Object getQcHouse() {
            return qcHouse;
        }

        public void setQcHouse(Object value) {
            this.qcHouse = value;
        }

        public Object getHistoryValues() {
            return historyValues;
        }

        public void setHistoryValues(Object value) {
            this.historyValues = value;
        }

        public Object getUnparsedParts() {
            return unparsedParts;
        }

        public void setUnparsedParts(Object value) {
            this.unparsedParts = value;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String value) {
            this.source = value;
        }

        public String getQc() {
            return qc;
        }

        public void setQc(String value) {
            this.qc = value;
        }
    }

// Metro.java

    public class Metro {
        private String name;
        private String line;
        private double distance;

        public String getName() {
            return name;
        }

        public void setName(String value) {
            this.name = value;
        }

        public String getLine() {
            return line;
        }

        public void setLine(String value) {
            this.line = value;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double value) {
            this.distance = value;
        }
    }

// Management.java

    public class Management {
        private String name;
        private String post;
        private Object disqualified;

        public String getName() {
            return name;
        }

        public void setName(String value) {
            this.name = value;
        }

        public String getPost() {
            return post;
        }

        public void setPost(String value) {
            this.post = value;
        }

        public Object getDisqualified() {
            return disqualified;
        }

        public void setDisqualified(Object value) {
            this.disqualified = value;
        }
    }

// Name.java

    public class Name {
        private String full_with_opf;
        private String short_with_opf;
        private Object latin;
        private String full;
        private String nameShort;

        public String getFullWithOpf() {
            return full_with_opf;
        }

        public void setFullWithOpf(String value) {
            this.full_with_opf = value;
        }

        public String getShortWithOpf() {
            return short_with_opf;
        }

        public void setShortWithOpf(String value) {
            this.short_with_opf = value;
        }

        public Object getLatin() {
            return latin;
        }

        public void setLatin(Object value) {
            this.latin = value;
        }

        public String getFull() {
            return full;
        }

        public void setFull(String value) {
            this.full = value;
        }

        public String getNameShort() {
            return nameShort;
        }

        public void setNameShort(String value) {
            this.nameShort = value;
        }
    }

    public class State {
        private String status;
        private String code;
        private long actualityDate;
        private long registrationDate;
        private Object liquidationDate;

        public String getStatus() { return status; }
        public void setStatus(String value) { this.status = value; }

        public String getCode() { return code; }
        public void setCode(String value) { this.code = value; }

        public long getActualityDate() { return actualityDate; }
        public void setActualityDate(long value) { this.actualityDate = value; }

        public long getRegistrationDate() { return registrationDate; }
        public void setRegistrationDate(long value) { this.registrationDate = value; }

        public Object getLiquidationDate() { return liquidationDate; }
        public void setLiquidationDate(Object value) { this.liquidationDate = value; }
    }
}