--1

SELECT artifact.artifact_name FROM student
    JOIN  student_2_buyers_group
        ON student.id_student = student_2_buyers_group.id_student
    JOIN group_transaction
        ON student_2_buyers_group.id_group = group_transaction.id_group
    JOIN artifact
        ON group_transaction.id_artifact = artifact.id_artifact
UNION 
SELECT  artifact.artifact_name FROM student
    JOIN student_transaction
        ON student.id_student = student_transaction.id_student
    JOIN artifact
        ON student_transaction.id_artifact = artifact.id_artifact;

--2

