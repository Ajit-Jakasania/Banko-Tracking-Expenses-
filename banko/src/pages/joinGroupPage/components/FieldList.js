import React, { useContext, useState } from 'react'
import { useForm } from 'react-hook-form';
import $ from 'jquery'
import styles from './FieldList.module.css'
import InputField from './InputField'
import InputButton from './InputButton'
import { Context } from '../../../Store';


function FieldList()
{
    const { joinGroup, handleSubmit, errors } = useForm(); // initialize the hook
    const {state, setState} = useContext(Context);


    const onSubmit = (jsonData) => {

        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/joinGroup',
            type: 'POST',
            dataType: 'json',
            data: "{\" " + jsonData.key + "\" : \"" + jsonData.value + "\" , \"user_id\" : \"" + state.id + "\" }",
            success: function (retValue) {

                console.log(retValue);
            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }

        });

    };
    
    return(
        
        <div>
            <form className={styles.FieldList} onSubmit={handleSubmit(onSubmit)}>
                <input placeholder="Group Name" name="group_name" ref={joinGroup({ required: true })} />
                {errors.first_name && 'Group name is required.'}
         <input type="submit" />

         </form>

     </div>
    );
}

export default FieldList