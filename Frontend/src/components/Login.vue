<template>
<div id="login">

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

        <div class="half right"  data-aos="fade-up">
            <div class="container" align="center">
                <div class="login-grid" style="padding-right:10%">
                    <input
                        class="login-text"
                        type="text"
                        placeholder="Email"
                        v-model="username"
                        />
                    <input
                        class="login-text"
                        type="password"
                        placeholder="Password"
                        v-model="password"
                        />
                    <input @click="login(username, password)"
                        type="submit"
                        value="Login"
                        class="btn btn-primary py-2 px-4 text-white"
                        />
                        <br>
                        <span v-if="errorLogin" style="color:red">Error: {{errorLogin}} </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script src="./javascript/login.js"></script>