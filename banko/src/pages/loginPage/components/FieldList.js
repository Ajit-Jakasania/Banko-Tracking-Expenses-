import React, { useContext } from 'react'

import $ from 'jquery'
import { useForm } from 'react-hook-form';

import styles from './FieldList.module.css'
import { Context } from '../../../Store'; //remove possibly


function FieldList() {

    const { register, handleSubmit, errors } = useForm(); // initialize the hook
    const [state, setState] = useContext(Context);    
    const onSubmit = (jsonData) => {

        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/userLogIn',
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(jsonData),
            success: function (retValue) {
                if (retValue == -1) console.log("bad");
                else {
                    console.log("your id is " + retValue);
                    sessionStorage.setItem("id", retValue);

                    setState({ id: retValue });  //remove later possibly

                }


            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }

        });

    };

    return (
        <div>
            <form className={styles.FieldList} onSubmit={handleSubmit(onSubmit)}>
                <ul className={styles}>
                    <li>User Login</li>
                    <li><input placeholder="Username" name="username" ref={register({ required: true })} /></li>
                    {errors.username && 'username is required.'}

                    <li><input type="password" placeholder="Password" name="hashed_password" ref={register({ required: true })} /></li>
                    {errors.hashed_password && 'Password is required.'}


                    <li><input type="submit" /></li>
                </ul>

            </form>

        </div>
    );
}

export default FieldList