<template>
<div id="register">

    <div class="site-section site-hero inner">
        <div class="half left">
            <div class="container" align="center">
                <div class="row align-items-center" align="center">
                    <div class="col-md-10" align="center" style="padding-left:10%">
                        <span class="d-block mb-3 caption">ECSE 321</span>
                        <h1 class="d-block mb-4">Coop-erator</h1>
                    </div>
                </div>
            </div>
        </div>

        <div class="half right">
            <div class="container" align="center">
                <div class="login-grid" style="padding-right:10%">
                    <input
                        class="login-text"
                        type="text"
                        placeholder="Username"
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
                        value="Register"
                        class="btn btn-primary py-2 px-4 text-white"
                        />
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script src="./javascript/login.js">
</script>
