

 INSERT INTO person(id, name, imageUrl, createdat, updatedat, version) VALUES ('0ca6271d-a96e-4864-9abc-15980c0de842', 'Yasir Shabbir', 'https://randomuser.me/api/portraits/men/33.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
INSERT INTO person(id, name, imageUrl, createdat, updatedat, version) VALUES ('cd5cd458-294d-4ef9-99eb-0a15848433c7', 'Mohit Manrai', 'https://randomuser.me/api/portraits/men/78.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
 INSERT INTO person(id, name, imageUrl, createdat, updatedat, version) VALUES ('cd5cd458-294d-4ef9-99eb-0a15841133c7', 'John Smith', 'https://randomuser.me/api/portraits/men/85.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);


INSERT INTO project(id, name,  createdat, updatedat, version) VALUES ('0ca6271d-a96e-4864-9abc-15980c0de842', 'Burj Khalifa',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
INSERT INTO project(id, name,  createdat, updatedat, version) VALUES ('cd5cd458-294d-4ef9-99eb-0a15848433c7', 'SOHA Tower',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
INSERT INTO project(id, name,  createdat, updatedat, version) VALUES ('cd5cd458-294d-4ef9-99eb-0a15848433c6', 'Dubai Digital Park',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);



  INSERT INTO task(id, title,description,priority,status,duedate, createdat,updatedat, version,person_id,project_id) VALUES ('0ca6271d-a96e-4864-9abc-15980c0de842', 'Construction Schedule Coordination', ' thorough examination of structures to ensure their safety, stability, and performance. It plays a crucial role in the design and construction process, contributing to the overall success and longevity of structures.','HIGH','PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 0,'0ca6271d-a96e-4864-9abc-15980c0de842','0ca6271d-a96e-4864-9abc-15980c0de842');
   INSERT INTO task(id, title,description,priority,status,duedate, createdat,updatedat, version,person_id,project_id) VALUES ('a275575a-b766-4d63-8d75-695a1e6dfe68', 'Structural Engineering Analysis', 'Applied to the design and analysis of buildings to ensure they meet safety and performance standards.','MEDIUM','PENDING', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0,'0ca6271d-a96e-4864-9abc-15980c0de842','0ca6271d-a96e-4864-9abc-15980c0de842');
  INSERT INTO task(id, title,description,priority,status,duedate, createdat,updatedat, version,person_id,project_id) VALUES ('0ca6271d-a96e-4864-9abc-15980c0de811', 'Architectural Blueprint Review', 'A detailed and technical drawing or plan that outlines the design specifications for a building or project. Blueprints serve as a visual guide for construction.','LOW','COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 0,'cd5cd458-294d-4ef9-99eb-0a15848433c7','cd5cd458-294d-4ef9-99eb-0a15848433c7');
   INSERT INTO task(id, title,description,priority,status,duedate, createdat,updatedat, version,person_id,project_id) VALUES ('a7258134-a62e-4c8a-bc65-179a08930b2f', 'Site Inspection', ' Evaluating the forces and loads (e.g., gravity, wind, earthquakes) that a structure will experience.','MEDIUM','PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 0,'cd5cd458-294d-4ef9-99eb-0a15848433c7','cd5cd458-294d-4ef9-99eb-0a15848433c7');

COMMIT;