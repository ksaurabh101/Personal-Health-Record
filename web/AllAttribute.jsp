<%-- 
    Document   : AllAttribute
    Created on : Feb 7, 2016, 11:37:03 PM
    Author     : Saurabh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            $(document).ready(function (){
                
            });
        </script>
    </head>
    <body>
        <div class="form-group">
            <div class="row">
                <div class="col-lg-1"></div>
                <div class="col-lg-2">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h4>Profession</h4></div>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="profession" value="Staff">Staff
                                    </label>
                                </div>
                            </li>
                            <li class="list-group-item"><div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="profession" value="Physicians">Physicians
                                    </label>
                                </div>
                            </li>
                            <li class="list-group-item"><div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="profession" value="Insurance">Insurance
                                    </label>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <!--col-lg-2 -->
                <div class="col-lg-1"></div>
                <div class="col-lg-4">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h4>Speciality</h4></div>

                        <div class="panel-group" id="accordion">
                            <div class="panel panel-default" id="staff1">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                                            Staff
                                        </a>
                                    </h5>
                                </div>
                                <div id="collapse1" class="panel-collapse collapse in">
                                    <ul class="list-group">
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="NURSING SISTER">NURSING SISTER
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="O.T. ASSISTANT">O.T. ASSISTANT
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="MIDWIFE">MIDWIFE
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="HEALTH VISITOR">HEALTH VISITOR
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="RADIOGRAPHER">RADIOGRAPHER
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="DRESSER">DRESSER
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="LABORATORY TECHNICIAN">LABORATORY TECHNICIAN
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="PHARMACIST">PHARMACIST
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="CLERK">CLERK
                                                </label>
                                            </div>
                                        </li><li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="THERAPIST">THERAPIST
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="DIETICIAN">DIETICIAN
                                                </label>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="panel panel-default" id="physicians1">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
                                            Physicians
                                        </a>
                                    </h5>
                                </div>
                                <div id="collapse2" class="panel-collapse collapse in">
                                    <ul class="list-group">

                                        <!-- Physician List -->
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Allergy and asthma">Allergy and asthma
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Anesthesiology">Anesthesiology
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Cardiology">Cardiology
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Dermatology">Dermatology 
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Endocrinology">Endocrinology
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Gastroenterology">Gastroenterology
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="General surgery">General surgery
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Hematology">Hematology
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Immunology">Immunology
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Nephrology">Nephrology
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Neurology">Neurology
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Obstetrics/gynecology">Obstetrics/gynecology
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Oncology">Oncology
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Ophthalmology">Ophthalmology
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Orthopedics">Orthopedics
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Otorhinolaryngology">Otorhinolaryngology
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Psychiatry">Psychiatry
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Pulmonary">Pulmonary
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Radiology">Radiology
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Rheumatology">Rheumatology
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Urology">Urology
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Dentist">Dentist
                                                </label>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="panel panel-default" id="insurance1">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">
                                            Insurance
                                        </a>
                                    </h5>
                                </div>
                                <div id="collapse3" class="panel-collapse collapse in">
                                    <ul class="list-group">
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Life Insurance">Life Insurance
                                                </label>
                                            </div>
                                        </li>
                                        <li class="list-group-item">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="speciality" value="Health Insurance">Health Insurance
                                                </label>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--col-lg-4 -->
                <div class="col-lg-1"></div>
                <div class="col-lg-2">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h4>Organization</h4></div>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="organization" value="Hospital A">Hospital A
                                    </label>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="organization" value="Hospital B">Hospital B
                                    </label>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="organization" value="Government">Government
                                    </label>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="organization" value="Private">Private
                                    </label>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="organization" value="Emergency">Emergency
                                    </label>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <!--col-lg-2 -->
            </div>
            <!-- /.row -->
        </div>
        <!--form-group -->
    </body>
</html>
