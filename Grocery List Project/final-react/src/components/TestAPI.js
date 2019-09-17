import React, { Component } from 'react';
import Search from "./Search";
import styled, { keyframes } from 'styled-components';
import {bounce} from 'react-animations';

const bounceAnimation = keyframes`${bounce}`;
const BouncyOnceDiv = styled.div`
  animation: 1s ${bounceAnimation} ;
`;


class TestAPI extends Component{

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoading: false,
            products: []
        };
    }

    componentWillMount() {
        this.setState({ isLoading: true });
        fetch('https://api-41200.herokuapp.com/products/?results=10')
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Something went wrong ...');
                }
            })
            .then((products) => {
                console.log(products);
                console.log(products.length);
                this.setState({
                    products: products,
                    isLoading: false,
                });
            });


        let items = [];
        let item = {ProductName: this.props.name, Brand: this.props.brand, Category: this.props.category, SubCategory: this.props.SubCategory, UPC12: this.props.upc12, UPC14: this.props.upc14, _id: this.props.id};
        items.push(item);

        this.setState({products: items});
    }



    render(){

        const { isLoading, error } = this.state;

        if (error) {
            return <p>{error.message}</p>;
        }
        if (isLoading) {
            return <p className="mt-4">Loading ...</p>;
        }

        return(
            <div>
                <div className="">
                    <BouncyOnceDiv className="mb-4">
                        <h1 className="header-wrapper">Grocery List App!</h1>
                    </BouncyOnceDiv>
                </div>
                <div className="search-wrapper">
                    <Search/>
                </div>
                <div className="">
                    <div className="mt-4 product-wrapper">
                        <ul>

                            {this.state.products.map((item, index) =>

                                <li key={index}>
                                    <p className="text-left">{item.ProductName}</p>
                                </li>
                            )}
                        </ul>
                    </div>
                </div>
            </div>
        );
    }
}

export default TestAPI;