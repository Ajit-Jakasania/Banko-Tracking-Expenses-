import React, { useContext, useState } from 'react'

import $ from 'jquery'
import { useForm } from 'react-hook-form'

import styles from './FieldList.module.css'
import InputField from './InputField';
import InputButton from './InputButton';
import { Context } from '../../../Store' //remove possibly


function FieldList() {

    const { register, handleSubmit, errors } = useForm(); // initialize the hook
    const [state, setState] = useContext(Context);
    const [message, setMessage] = useState("");
    const onSubmit = (jsonData) => {

        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/userLogIn',
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(jsonData),
            success: function (retValue) {
                if (retValue == -1) setMessage("Incorrect Information Entered!");

                else {
                    sessionStorage.setItem("id", retValue);

                    setState({ id: retValue });  //remove later possibly
                    setMessage("Logged In!");
                }


            },
            error: function (request, status, error) {
                setMessage("Failed HTTP request!");

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


                    <li><input type="submit" value="Log In" /></li>
                </ul>

            </form>
            <p>{message}</p>
        </div>
    );
}

export default FieldList