import React from 'react'
import { useForm } from 'react-hook-form';
import $ from 'jquery'
import styles from './FieldList.module.css'
import InputField from './InputField'
import InputButton from './InputButton'

function FieldList()
{
    const { createGroup, handleSubmit, errors } = useForm(); // initialize the hook

    const onSubmit = (jsonData) => {

        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/createBankGroup',
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

    return(
        
        <div>
            <form className={styles.FieldList} onSubmit={handleSubmit(onSubmit)}>
                <input placeholder="Group Name" name="group_name" ref={createGroup({ required: true })} />
                {errors.first_name && 'Group name is required.'}
                
                <input type="submit" />

         </form>

     </div>
    );
}

export default FieldList