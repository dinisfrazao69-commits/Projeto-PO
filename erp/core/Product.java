package erp.core;

import java.io.Serializable;

public abstract class Product implements Serializable{
    
    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    private String _name;
    private int _id;
    private ProductCategory _category;
    private int _basePrice;
    private int _baseDeliveryTime;
    
    public Product(int id, String name, ProductCategory category, int basePrice, int baseDeliveryTime){
        this._id = id;
        this._name = name;
        this._category = category;
        this._baseDeliveryTime = baseDeliveryTime;
        this._basePrice = basePrice;
    }


    public int getId(){
        return this._id;
    }

    public String getName(){
        return this._name;
    }

    public ProductCategory getCategory(){
        return this._category;
    }

    public int getBasePrice(){
        return this._basePrice;
    }

    public int getBaseDeliveryTime(){
        return this._baseDeliveryTime;
    }

    public int totalUnits(){
        return 0; //Alterar quando estivermos a mexer nos stocks e assim
    }

    public abstract String type();

    public abstract double recommendedPrice();


    @Override
    public String toString(){
        return this._id + " - " + type() + " - " + this._name + " - " + this._category +
        " - " + Format.money(this._basePrice) + " - " + Format.money(this.recommendedPrice())
        + " - " + this._baseDeliveryTime + " - " + totalUnits();
    }
}