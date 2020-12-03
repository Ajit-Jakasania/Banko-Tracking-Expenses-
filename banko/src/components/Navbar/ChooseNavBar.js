import React, { useContext, useEffect, useState } from 'react';

import { Context } from '../../Store';
import NavbarDefault from './NavbarDefault';
import NavbarLoggedIn from './NavbarLoggedIn';

function ChooseNavBar() {

    //const [state, setState] = useState(0);
    const [isUserLoggedIn, setUserLoggedIn] = useContext(Context);




    if (!isUserLoggedIn.id) {
        return (
            <NavbarDefault />
        );
    }
    return (
        <NavbarLoggedIn />
    )
}



export default ChooseNavBar;