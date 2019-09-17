import React, { Component } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Fuse from 'fuse.js';

const searchOptions = {
    shouldSort: true,
    findAllMatches: true,
    threshold: 1,
    location: 0,
    distance: 100,
    maxPatternLength: 32,
    minMatchCharLength: 1,
    keys: [
        "Brand",
        "ProductName"
    ]
}

class Search extends Component{
    constructor(props){
        super(props);
        this.state = {
            stores: [],
            categories: [],
            subcategories: [],
            selectedCategory: "",
            selectedSubcategory: "",
            searchInput: "",
        };
        this.PlacesService = new window.google.maps.places.PlacesService(document.createElement('div'));
    }

    componentDidMount(){
        this.getStores();
        this.getCategories();
    }

    getStores = () => {
        if ("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition((position) => {
                var loc = new window.google.maps.LatLng(position.coords.latitude,position.coords.longitude);
                this.PlacesService.nearbySearch({
                    location: loc,
                    radius: '20000',
                    type: ['supermarket']
                }, this.setStores);
            });
        } else {
        /* geolocation IS NOT available */
        }
    }

    setStores = (results, status) => {
        if (status === window.google.maps.places.PlacesServiceStatus.OK) {
            this.setState({
                stores: results
            });
        }
    }

    handleStoreChange = (e) => {
        this.props.setSelectedStore(e.target.value);
    }

    getCategories = () => {
        fetch("https://api-41200.herokuapp.com/products/categories/")
            .then(response => response.json())
            .then(data => this.setState({ categories: data.categories }));
    }

    getSubcategories = (category) => {
        this.setState({selectedSubcategory: ""})
        fetch(`https://api-41200.herokuapp.com/products/categories/${category}/subcategories/`)
            .then(response => response.json())
            .then(data => this.setState({ subcategories: data.subcategories }));
    }

    handleCategoryChange = (e) => {
        this.setState({
            selectedCategory: e.target.value
        });
        if(e.target.value !== ""){
            this.getSubcategories(e.target.value);
        }
    }

    handleSubcategoryChange = (e) => {
        this.setState({
            selectedSubcategory: e.target.value
        });
    }

    handleSearchInput = (e) => {
        this.setState({
            searchInput: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        const cat = this.state.selectedCategory;
        const sub = this.state.selectedSubcategory;
        if(cat !== "" && sub !== ""){
            fetch(`https://api-41200.herokuapp.com/products/subcategories/${sub}`)
                .then(response => response.json())
                .then(data => {
                    const products = data;
                    const fuse = new Fuse(products, searchOptions);
                    const results = fuse.search(this.state.searchInput);
                    if(this.state.searchInput !== ""){
                        this.props.setProducts(results);
                    }else{
                        this.props.setProducts(data)
                    }
                });
        }else if(cat !== "" && sub === ""){
            fetch(`https://api-41200.herokuapp.com/products/categories/${cat}`)
                .then(response => response.json())
                .then(data => {
                    const products = data;
                    const fuse = new Fuse(products, searchOptions);
                    const results = fuse.search(this.state.searchInput);
                    if(this.state.searchInput !== ""){
                        this.props.setProducts(results);
                    }else{
                        this.props.setProducts(data)
                    }
                });
        }
    }

    render(){
        return(
            <div>
                <div className="search-wrapper">
                    <Form onSubmit={this.handleSubmit}>
                        <div className="d-flex flex-row">
                            <Form.Group className="mr-4 col-2" controlId="store">
                                <Form.Label>Store</Form.Label>
                                <Form.Control as="select" onChange={this.handleStoreChange} value={this.props.selectedStore}>
                                    {this.state.stores.length
                                    ? this.state.stores.map((store, i)=>{
                                        if(i===0){
                                            return [<option value="" key={i}>Choose a Store</option>,<option value={JSON.stringify(store)} key={JSON.stringify(store)}>{`${store.name} - ${store.vicinity}`}</option>]
                                        }else{
                                            return <option value={JSON.stringify(store)} key={JSON.stringify(store)}>{`${store.name} - ${store.vicinity}`}</option>
                                        }
                                    })
                                    : <option value="">Loading...</option>}
                                </Form.Control>
                            </Form.Group>
                            <Form.Group className="mr-4 col-2" controlId="category">
                                <Form.Label>Category</Form.Label>
                                <Form.Control as="select" onChange={this.handleCategoryChange} value={this.state.selectedCategory}>
                                    <option value="">Choose a Category</option>
                                    {this.state.categories.map((category,i)=>{
                                        return <option value={category} key={category}>{category}</option>
                                    })}
                                </Form.Control>
                            </Form.Group>
                            <Form.Group className="mr-4 col-2" controlId="sub-category">
                                <Form.Label>Sub Category</Form.Label>
                                <Form.Control as="select" onChange={this.handleSubcategoryChange} value={this.state.selectedSubcategory}>
                                    <option value="">Choose a Sub Category</option>
                                    {this.state.subcategories.map((subcategory, i)=>{
                                        return <option value={subcategory} key={subcategory}>{subcategory}</option>
                                    })}
                                </Form.Control>
                            </Form.Group>
                            <Form className="mt-3 col-8 ml-auto" inline>
                                <Form.Control type="text" placeholder="Search" className="mr-sm-2" value={this.state.searchInput} onChange={this.handleSearchInput}/>
                                <Button variant="outline-success" type="submit">Search</Button>
                            </Form>
                        </div>
                    </Form>
                </div>
            </div>
        );
    }
}

export default Search;