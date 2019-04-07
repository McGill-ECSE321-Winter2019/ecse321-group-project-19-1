<template>
<div id="register">

    <div class="site-section site-hero inner">
        <div class="half left">
            <div class="container" align="center">
                <div class="row align-items-center" align="center">
                    <div class="col-md-10" align="center" style="padding-left:10%">
                        <span class="d-block mb-3 caption">ECSE 321</span>
                        <img src="../assets/nameLogo.svg" style="width:65%">
                    </div>
                </div>
            </div>
        </div>

        <div class="half right" data-aos="fade-up">
            <div class="container" align="center">
                    <select v-model="selected">
                    <option value="Select">Select</option>
                    <option value="TermInstructor">Term Instructor</option>
                    <option value="ProgramManager">Program Manager</option>
                    </select>
                    <hr>
                    <input
                        class="login-text"
                        type="text"
                        placeholder="First Name"
                        v-model="firstName"
                        />
                    <input
                        class="login-text"
                        type="text"
                        placeholder="Last Name"
                        v-model="lastName"
                    />
                    <input
                        class="login-text"
                        type="text"
                        placeholder="Email"
                        v-model="email"
                        />
                    <input
                        class="login-text"
                        type="password"
                        placeholder="Password"
                        v-model="password"
                        />
                    <input @click="register(firstName, lastName, email, password, selected)"
                        type="submit"
                        value="Register"
                        class="btn btn-primary py-2 px-4 text-white"
                        />
                        <br>
                        <span v-if="errorRegister" style="color:red">Error: {{errorRegister}} </span>
                        <span v-if="response" style="color:green">Success: {{response}} </span>
                </div>
            </div>
        </div>
    </div>
</template>

<script src="./javascript/register.js">
</script>
