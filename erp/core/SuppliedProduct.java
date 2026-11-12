package erp.core;

import java.io.Serializable;

public class SuppliedProduct implements Serializable{
    
    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    private Company _associatedCompany;
    private Product _product;
    private int _quantity;
    private double _factor;

    public SuppliedProduct(Company company, Product product, int quantity, double factor){
        this._associatedCompany = company;
        this._product = product;
        this._quantity = quantity;
        this._factor = factor;

    }

    public Company getCompany(){
        return this._associatedCompany;
    }

    public Product getProduct(){
        return this._product;
    }

    public int getQuantity(){
        return this._quantity;
    }

    public double getFactor(){
        return this._factor;
    }

    double price(){
        double preco = this._product.recommendedPrice();
        preco = preco * this._factor;
        preco = Math.round(preco*100)/ 100.0;
        return preco;
    }

    int baseDeliveryTime(){
        return this._product.getBaseDeliveryTime();
    }

    boolean update(int unity, double factor){
        int quantidade = this._quantity + unity;
        if (quantidade < 0 || factor <= 0){
            return false;
        }
        this._quantity = quantidade;
        this._factor = factor;
        return true;
    }

    @Override 
    public String toString(){
        return this._associatedCompany.getId() + " - " + this._product.getId() + " - " + this._quantity + " - " + this._factor +
        " - " + Format.money(this.price());
    }
}
