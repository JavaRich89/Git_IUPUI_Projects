import React from "react";
import ProductCard from "../components/ProductCard";
import CardDeck from 'react-bootstrap/CardDeck';


class ProductCardList extends React.Component {

    render() {
        return (
        <div className="product-card-wrapper">
            <div className="product-card-spacing d-flex flex-row mb-4">
                <ProductCard className=""/>
                <ProductCard className=""/>
                <ProductCard className=""/>
            </div>
            <div className="product-card-spacing d-flex flex-row mb-4">
                <ProductCard className=""/>
                <ProductCard className=""/>
                <ProductCard className=""/>
            </div>
            <div className="product-card-spacing d-flex flex-row mb-4">
                <ProductCard className=""/>
                <ProductCard className=""/>
                <ProductCard className=""/>
            </div>

            {/*For when we need to loop through items*/
            /*<CardDeck>
                <div className = "row">
                <ProductCard
                    for = "(product, index) in products"
                    bind:key = "index"
                    bind:product = "product"
                    className = "col-12 col-md-6 col-lg-4"
                />
                </div>
            </CardDeck>*/}

        </div>

        )};
}
export default (ProductCardList);