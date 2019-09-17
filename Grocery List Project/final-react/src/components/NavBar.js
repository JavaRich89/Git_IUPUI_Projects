import React from 'react'
import AppBar from '@material-ui/core/AppBar'
import Nav from '../components/Nav';
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'
import Button from 'react-bootstrap/Button';
import {withFirebase} from './Firebase';
import {withAuthUser} from './Session';
import {compose} from 'recompose';
const NavBar = ({firebase, authUser}) => {
    return(
        <div>
            <AppBar position="static">
                <Toolbar className="">
                    <Typography variant="title" color="inherit" align="center">
                        Cloud List
                    </Typography>

                    <div className="ml-4 pl-4 mt-1">
                        <Nav className="nav-center"/>
                    </div>
                    <div className="ml-auto">
                        {authUser
                            ? <Button variant="default" onClick={firebase.doSignOut}>Logout</Button>
                            : <Button variant="default" onClick={firebase.doSignInWithGoogle}>Login</Button>
                        }
                    </div>
                </Toolbar>
            </AppBar>
        </div>
    )
};
export default compose(withFirebase, withAuthUser)(NavBar);