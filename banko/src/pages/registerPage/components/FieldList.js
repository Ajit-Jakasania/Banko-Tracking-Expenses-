import React, { useEffect, useState } from 'react'
import { useForm } from 'react-hook-form';
import $ from 'jquery'

import styles from './FieldList.module.css'
import { Context } from '../../../Store';

function FieldList() {
    const { register, handleSubmit, errors } = useForm(); // initialize the hook

    //const [returnValue, setReturnValue] = useState("hihihi");
    const onSubmit = (jsonData) => {

        //removes empty values AKA optionals
        $.each(jsonData, function (key, value) {
            if (value === "" || value === null) {
                delete jsonData[key];
            }
        });

        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/userRegister',
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(jsonData),
            success: function (retValue) {

                console.log(retValue);
                //setReturnValue(retValue);
            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }
        });

    };


    //change the title!!!! on line 43
    return (


        <div>

            <form className={styles.FieldList} onSubmit={handleSubmit(onSubmit)}>
                <ul className={styles}>

                    <li>Register</li>
                    <li><input placeholder="First Name" name="first_name" ref={register({ required: true })} /></li>
                    {errors.first_name && 'First name is required.'}

                    <li><input placeholder="Last name" name="last_name" ref={register({ required: true })} /></li>
                    {errors.last_name && 'Last name is required.'}

                    <li><input placeholder="email" name="email" ref={register({ required: true })} /></li>
                    {errors.email && 'Email is required.'}

                    <li><input placeholder="Phone Number" name="phone_number" ref={register()} /></li>

                    <li><input placeholder="Street Name" name="street_name" ref={register({ required: true })} /></li>
                    {errors.street_name && 'Street name is required.'}

                    <li><input placeholder="Zipcode" name="zip_code" ref={register({ pattern: /\d+/ })} /></li>

                    <li><input placeholder="City" name="city_name" ref={register({ required: true })} /></li>
                    {errors.city_name && 'City name is required.'}

                    <li><input placeholder="Country" name="country_name" ref={register({ required: true })} /></li>
                    {errors.country_name && 'Country name is required.'}

                    <li><input placeholder="Username" name="username" ref={register({ required: true })} /></li>
                    {errors.username && 'username is required.'}

                    <li><input type="password" placeholder="Password" name="hashed_password" ref={register({ required: true })} /></li>
                    {errors.hashed_password && 'Password is required.'}

                    <li><input placeholder="Birth Month" name="birth_month" ref={register({ required: true, pattern: /\d+/ })} /></li>
                    {errors.birth_month && 'Please enter number for month.'}

                    <li><input placeholder="Birth Day" name="birth_day" ref={register({ required: true, pattern: /\d+/ })} /></li>
                    {errors.birth_day && 'Please enter number for day.'}

                    <li><input placeholder="Birth Year" name="birth_year" ref={register({ required: true, pattern: /\d+/ })} /></li>
                    {errors.birth_year && 'Please enter number for year.'}

                    <li><input type="submit" value="Register" /></li>


                </ul>

            </form>

        </div>
    );
}

export default FieldList