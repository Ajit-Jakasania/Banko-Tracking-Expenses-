import React from 'react'
import { useForm } from 'react-hook-form';
import $ from 'jquery'

import styles from './FieldList.module.css'
import InputField from './InputField'
import InputButton from './InputButton'

function FieldList() {
    const { register, handleSubmit, errors } = useForm(); // initialize the hook

    const onSubmit = (jsonData) => {

        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/userRegister',
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(jsonData),
            success: function (retValue) {

                console.log(retValue);
            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }

        });

    };



    return (


        <div>
            <form className={styles.FieldList} onSubmit={handleSubmit(onSubmit)}>
                <input placeholder="First Name" name="first_name" ref={register({ required: true })} />
                {errors.first_name && 'First name is required.'}

                <input placeholder="Last name" name="last_name" ref={register({ required: true })} />
                {errors.last_name && 'Last name is required.'}

                <input placeholder="email" name="email" ref={register({ required: true })} />
                {errors.email && 'Email is required.'}

                <input placeholder="Phone Number" name="phone_number" ref={register()} />

                <input placeholder="Street Name" name="street_name" ref={register({ required: true })} />
                {errors.street_name && 'Street name is required.'}

                <input placeholder="Zipcode" name="zip_code" ref={register({ pattern: /\d+/ })} />

                <input placeholder="City" name="city_name" ref={register({ required: true })} />
                {errors.city_name && 'City name is required.'}

                <input placeholder="Country" name="country_name" ref={register({ required: true })} />
                {errors.country_name && 'Country name is required.'}

                <input placeholder="Username" name="username" ref={register({ required: true })} />
                {errors.username && 'username is required.'}

                <input placeholder="Password" name="hashed_password" ref={register({ required: true })} />
                {errors.hashed_password && 'Password is required.'}

                <input placeholder="Birth Month" name="birth_month" ref={register({ required: true, pattern: /\d+/ })} />
                {errors.birth_month && 'Please enter number for month.'}

                <input placeholder="Birth Day" name="birth_day" ref={register({ required: true, pattern: /\d+/ })} />
                {errors.birth_day && 'Please enter number for day.'}

                <input placeholder="Birth Year" name="birth_year" ref={register({ required: true, pattern: /\d+/ })} />
                {errors.birth_year && 'Please enter number for year.'}


                <input type="submit" />

            </form>

        </div>
    );
}

export default FieldList