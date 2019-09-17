import React from "react";
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Images from '../components/Images';
import {compose} from 'recompose';
import {withAuthUser} from './Session';

class ProductCard extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoading: false
        };
    }

    componentWillMount() {
        this.setState({ isLoading: true });
        fetch('https://api-41200.herokuapp.com/products/')
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Something went wrong ...');
                }
            })
            .then((products) => {
                this.props.setProducts(products);
                this.setState({
                    isLoading: false,
                });
            });
    }

    handleAddToList = (e) => {
        if(this.props.selectedStore){
            if(this.props.authUser){
                const product = JSON.parse(e.target.value);
                const store = JSON.parse(this.props.selectedStore);
                console.log(store);
                fetch(`https://api-41200.herokuapp.com/lists/${this.props.authUser.uid}`)
                .then(response => response.json())
                .then(data => {
                    if(data.length){
                        let body = data[0];
                        let storeFound = false;
                        body.stores.map(e=>{
                            if(e.place_id === store.place_id){
                                e.products.push(product);
                                storeFound = true;
                            }
                        });
                        if(!storeFound){
                            let storeObj = {};
                            storeObj.place_id = store.place_id;
                            storeObj.name = `${store.name} - ${store.vicinity}`;
                            storeObj.products = [];
                            storeObj.products.push(product);
                            body.stores.push(storeObj);
                        }
                        return fetch(`https://api-41200.herokuapp.com/lists/${body._id}`,{
                            method: "PUT",
                            headers: {
                                "Content-Type": "application/json",
                            },
                            body: JSON.stringify(body)
                        })
                    }else{
                        let body = {};
                        body.uuid = this.props.authUser.uid;
                        body.stores = [];
                        let storeObj = {};
                        storeObj.place_id = store.place_id;
                        storeObj.name = `${store.name} - ${store.vicinity}`;
                        storeObj.products = [];
                        storeObj.products.push(product);
                        body.stores.push(storeObj);
                        return fetch("https://api-41200.herokuapp.com/lists/",{
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json",
                            },
                            body: JSON.stringify(body)
                        })
                    }
                })
                .catch(error=>console.error(error));
            }
        }
    }

    render() {

        const { isLoading, error } = this.state;

        if (error) {
            return <p>{error.message}</p>;
        }
        if (isLoading) {
            return <p className="mt-4">Loading ...</p>;
        }

        return (
        <div className="product-card-wrapper row">

            {this.props.products.map((item, index) =>
                <div className="mb-4 col-12 col-md-6 col-lg-4" key={index}>
                    <Card key={index} className="box-shadow  " style={{ width: '18rem' }}>
                        <Card.Img variant="top" src={Images.item} />
                        <Card.Body>
                            <Card.Title className="pb-4">{item.ProductName}</Card.Title>
                            <div className="d-flex flex-row">
                                <Card.Text>
                                    <div>
                                        {item.SubCategory}
                                    </div>
                                </Card.Text>
                                <Button className="ml-auto" variant="primary" size="sm" value={JSON.stringify(item)} onClick={this.handleAddToList}>Add to List</Button>
                            </div>
                        </Card.Body>
                    </Card>
                </div>
            )}
        </div>
        )};
}
export default compose(withAuthUser)(ProductCard);