import React, { useContext, useState } from 'react'
import { useForm } from 'react-hook-form';
import $ from 'jquery'
import styles from './FieldList.module.css'
import { Context } from '../../../Store';

function FieldList() {
    const { register, handleSubmit, errors } = useForm(); // initialize the hook
    const [state, setState] = useContext(Context);
    const [text, setText] = useState("");

    const onSubmit = (jsonData) => {

        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'https://gothic-point-298207.uc.r.appspot.com/transaction',
            type: 'POST',
            dataType: 'json',
            data: "{\"group_name\" : \"" + jsonData.group_name + "\" , \"transaction_content\" : \"" + jsonData.transaction_content + "\" , \"amount\" : \"" + jsonData.amount + "\" }",
            success: function (retValue) {

                console.log(retValue);
            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }

        });

        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'https://gothic-point-298207.uc.r.appspot.com/bill',
            type: 'POST',
            dataType: 'json',
            data: "{\"group_name\" : \"" + jsonData.group_name + "\" , \"photo_url\" : \"" + jsonData.photo_url + "\" }",
            success: function (retValue) {

                setText(retValue);
            },
            error: function (request, status, error) {
                setText(request.responseText);
            }

        });

    };

    return (

        <div>
            <form className={styles.FieldList} onSubmit={handleSubmit(onSubmit)}>
                <ul className={styles}>
                    <li>Create Transaction</li>
                    <li><input placeholder="Group Name" name="group_name" ref={register({ required: true })} /></li>
                    {errors.first_name && 'Group name is required.'}
                    <li><input placeholder="Amount to Pay" name="amount" ref={register({ required: true })} /></li>
                    {errors.first_name && 'Amount required'}
                    <li><input placeholder="Message" name="transaction_content" ref={register({ required: true })} /></li>
                    {errors.first_name && 'Message for transaction is required.'}
                    <li><input placeholder="Provide URL for Bill" name="photo_url" ref={register({ required: true })} /></li>
                    {errors.first_name && 'Photo URL required'}

                    <input type="submit" />

                    <p>{text}</p>
                </ul>
            </form>

        </div>
    );
}

export default FieldList