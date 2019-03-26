<template>
<div id="id">
        <div class="site-wrap">
            <div class="site-section" style="padding-top:10%">
                <div class="container">
                    <h1 class="page-title" align="center"> Student: {{student.firstName}} {{student.lastName}}</h1>
                    <h3 class="page-title" align="center"> 
                        ID: <span style="color:red">{{student.studentId}} </span>
                        , CoopID:<span style="color:red"> {{student.coopPositions[0].coopID}}</span>
                        , Problematic Status: <span style="color:red">{{student.problematic}}</span>
                        </h3>
                    <h2 class="page-title" align="center">Current Coop Status: {{student.coopPositions[0].status}}</h2>
                    
                    <b-table ref="table" class="info-table" striped hover :items= "documents" :fields= "fields"/>
                    <div style="width:100%" align="right">
                    <input @click="adj()"
                        style="background:green; border-color:green"
                        type="submit"
                        value="Adjudicate"
                        class="btn btn-primary"
                        />
                    </div>
                     </div>
                </div>
            </div>
        </div>
</template>

<script src="./javascript/PmSingleStudent.js">
</script>