<template>
<div id="app">
    <div class="site-wrap" style="height:100%">
        <div class="site-mobile-menu">
            <div class="site-mobile-menu-header">
                <div class="site-mobile-menu-close mt-3">
                    <span class="icon-close2 js-menu-toggle"></span>
                </div>
            </div>
            <div class="site-mobile-menu-body">
            </div>
        </div>

        <router-view></router-view>

        <footer class="site-footer">
            <div class="container">

                <div class="text-center">
                    <div class="border-top pt-5">
                        <p>
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            Copyright &copy;
                            All rights reserved | This template is made with
                            <i class="icon-heart text-primary" aria-hidden="true"></i> by
                            <a href="https://colorlib.com" target="_blank">Colorlib</a>
                            | @Authors BRODEUR Max, DENG Sophie, ELKHOURY Carl, KABA Andre, ZHOU Mia
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        </p>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>
</template>

<script>
export default {
    name: 'app'
}
</script>

<style>
@import 'style/css/style.css';
</style>
