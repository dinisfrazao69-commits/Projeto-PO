package erp.core;


public class NationalProduct extends Product{

    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    public NationalProduct(int id, String name, ProductCategory category, int basePrice, int baseDeliveryTime){
        super(id, name, category, basePrice, baseDeliveryTime);
    }

    @Override 
    public String type(){
        return "NACIONAL";
    }

    @Override 
    public double recommendedPrice(){
        int units = totalUnits();
        int basePrice = getBasePrice();

        if (units > 1000){
            return basePrice * 0.9;
        }
        if (units < 100){
            return basePrice * 1.3;
        }
        return basePrice;
    }
}