import java.util.HashMap;

public class HotelManager {

    public HashMap<Integer,Hotel> hotelHashMap = new HashMap<Integer,Hotel>();

    public void addHotel(int i, Hotel h) {

        hotelHashMap.put(i,h);

    }

    public void removeHotel(int h) {

        hotelHashMap.remove(h);

    }

    public int getSize() {

        return hotelHashMap.size();

    }

    public Hotel getHotels(int n) {

        return hotelHashMap.get(n);

    }

}
