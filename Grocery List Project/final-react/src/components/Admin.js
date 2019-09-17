import React, { Component } from 'react';


console.clear();


/* ProductForm */
class Admin extends React.Component {



    constructor(props) {
        super(props);
        this.state = {
            productList: ""
        };
    }

    createProduct(product) {
        this.setState({
            products: this.state.productList.push(product)
        });
    }

    submit(e) {
        e.preventDefault();
        var product = {
            name: this.refs.name.value,
            brand: this.refs.brand.value,
            upc12: Number(this.refs.upc12.value),
            upc14: Number(this.refs.upc14.value),
            category: this.refs.category.value,
            subCategory: this.refs.subCategory.value
        };
        console.log(this.refs.name.value, this.refs.brand.value, this.refs.upc12.value, this.refs.upc14.value, this.refs.category.value, this.refs.subCategory.value);
        this.props.createProduct(product);
        this.refs.name.value = "";
        this.refs.brand.value = "";
        this.refs.upc12.value = 0;
        this.refs.upc14.value = 0;
        this.refs.category.value = "";
        this.refs.subCategory.value = "";

    }



    render() {

        return (

            <form className="admin-form-wrapper pl-4 pt-4" onSubmit={this.submit.bind(this)}>

                    <h3 className="pb-2 col-sm-6 text-center">add new product</h3>

                <div className="row form-group">
                    <label className="col-sm-2  col-sm-form-label mt-1 text-right">product name:</label>
                    <div className="col-sm-8">
                        <input
                            type="text"
                            className="form-control"
                            ref="name"
                            placeholder="e.g.) Earth's Best Organic Fruit Yogurt"
                            required
                        />
                    </div>
                </div>

                <div className="row form-group">
                    <label className="col-sm-2  col-sm-form-label mt-1 text-right">brand:</label>
                    <div className="col-sm-8">
                        <input
                            type="text"
                            className="form-control"
                            ref="brand"
                            placeholder="e.g.) Earth's Best"
                        />
                    </div>
                </div>

                <div className="row form-group">
                    <label className="col-sm-2  col-sm-form-label mt-1 text-right">upc 12:</label>
                    <div className="col-sm-8">
                        <input
                            type="number"
                            className="form-control"
                            ref="upc12"
                            placeholder="e.g.) 12 digit UPC"
                        />
                    </div>
                </div>

                <div className="row form-group">
                    <label className="col-sm-2  col-sm-form-label mt-1 text-right">upc 14:</label>
                    <div className="col-sm-8">
                        <input
                            type="number"
                            className="form-control"
                            ref="upc14"
                            placeholder="e.g.) 14 digit UPC"
                        />
                    </div>
                </div>

                <div className="row form-group">
                    <label className="col-sm-2  col-sm-form-label mt-1 text-right">category:</label>
                    <div className="col-sm-8">
                        <input
                            type="text"
                            className="form-control"
                            ref="category"
                            placeholder="e.g.) Dairy"
                            required
                        />
                    </div>
                </div>

                <div className="row form-group">
                    <label className="col-sm-2  col-sm-form-label mt-1 text-right">sub-category:</label>
                    <div className="col-sm-8">
                        <input
                            type="text"
                            className="form-control"
                            ref="sub-category"
                            placeholder="e.g.) Yogurt"
                            required
                        />
                    </div>
                </div>



                <div className="row form-group">
                    <div className="col-sm-2"> </div>
                    <div className=" col-2">
                        <button onClick={this.createProduct} className="btn btn-block btn-outline-primary">create product</button>
                    </div>
                </div>

                <hr />
            </form>


        );
    }
}





export default Admin;