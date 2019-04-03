<template>
<div id="id">
    <div class="site-wrap">
        <div class="site-section" style="padding-top:10%">
            <div class="container" style="align:center">
                <h1 class="page-title">Assign Coop Position</h1>
                <input
                        class="text"
                        type="text"
                        placeholder="CoopID"
                        v-model="coopId"
                        />
                <input
                        class="text"
                        type="text"
                        placeholder="Instructor Email"
                        v-model="instructor"
                        />
                <input @click="assignCoop(instructor, coopId)"
                        type="submit"
                        value="Assign"
                        class="btn btn-primary py-2 px-4 text-white"
                        />
                <div class="container" align="center">
                    <span v-if="errorCoop" style="color:red">Error: {{errorCoop}} </span>
                    <br>
            </div>
            <div id="table">
                            <b-table ref="table" class="info-table" striped :items= "coops" :fields= "fields"/>
                     </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script src="./javascript/coop.js">
</script>
