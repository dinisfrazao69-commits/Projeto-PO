package erp.core;


public class ImportedProduct extends Product{

    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    private double _tax;
    private String _country;

    public ImportedProduct(int id, String name, ProductCategory category, int basePrice, int baseDeliveryTime,
        double tax, String country){

        super(id, name, category, basePrice, baseDeliveryTime);
        this._tax = tax;
        this._country = country;
    }

    public double getTax(){
        return this._tax;
    }

    public String getCountry(){
        return this._country;
    }


    @Override 
    public String type(){
        return "IMPORTADO";
    }

    @Override 
    public double recommendedPrice(){
        double tax = getTax();
        int basePrice = getBasePrice();
        return basePrice * (100 + tax)/100;
    }

    @Override 
    public String toString(){
        return super.toString() + " - " + this._country + " - " + this._tax;

    }
}