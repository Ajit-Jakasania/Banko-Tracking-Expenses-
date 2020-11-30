import React from 'react'
import $ from 'jquery'
import { useForm } from 'react-hook-form';

import styles from './FieldList.module.css'
import InputField from './InputField'
import InputButton from './InputButton'

function FieldList() {

    const { register, handleSubmit, errors } = useForm(); // initialize the hook

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


                <input placeholder="Username" name="username" ref={register({ required: true })} />
                {errors.username && 'username is required.'}

                <input placeholder="Password" name="hashed_password" ref={register({ required: true })} />
                {errors.hashed_password && 'Password is required.'}


                <input type="submit" />

            </form>

        </div>
    );
}

export default FieldList