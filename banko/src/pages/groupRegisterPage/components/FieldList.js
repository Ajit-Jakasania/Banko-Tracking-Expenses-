import React, { useContext } from 'react'
import { useForm } from 'react-hook-form';
import $ from 'jquery'
import styles from './FieldList.module.css'
import { Context } from '../../../Store';

function FieldList()
{
    const { register, handleSubmit, errors } = useForm(); // initialize the hook
    const [state, setState] = useContext(Context);

    const onSubmit = (jsonData) => {

        $.ajax({
            contentType: "application/json;charset=utf-8",
            url: 'http://localhost:8080/createBankGroup',
            type: 'POST',
            dataType: 'json',
            data: "{\"group_name\" : \"" + jsonData.group_name + "\" , \"user_id\" : \"" + state.id + "\" }",
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
                <ul className={styles}>
                    <li>Create A New Group</li>
                    <li><input placeholder="Group Name" name="group_name" ref={register({ required: true })} /></li>
                    {errors.first_name && 'Group name is required.'}
                    
                    <input type="submit" />
                </ul>
         </form>

     </div>
    );
}

export default FieldList