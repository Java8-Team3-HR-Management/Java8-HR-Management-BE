# PROJECT FLOW CHART 
[<img src="https://hizliresim.com/a4tbxce"/>](https://private-user-images.githubusercontent.com/123380760/272917023-d0567c5e-c9a2-4f64-96e5-1d8675c71393.jpg?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTEiLCJleHAiOjE2OTY1MTI1MzIsIm5iZiI6MTY5NjUxMjIzMiwicGF0aCI6Ii8xMjMzODA3NjAvMjcyOTE3MDIzLWQwNTY3YzVlLWM5YTItNGY2NC05NmU1LTFkODY3NWM3MTM5My5qcGc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBSVdOSllBWDRDU1ZFSDUzQSUyRjIwMjMxMDA1JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDIzMTAwNVQxMzIzNTJaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT00ZTM1MDhkYzEyNWQ3ZjIyMTNkMjdkYzEwMDgxYzA2NDg3NDY1M2VlMzE1ZTVlY2EwYThiNWJmZDFlZjg1MTVkJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.Ta2gzQc97z4ZLI5nIzp9Fja-SvUtaLoW4s9hjxQVg9s)https://private-user-images.githubusercontent.com/123380760/272917023-d0567c5e-c9a2-4f64-96e5-1d8675c71393.jpg?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTEiLCJleHAiOjE2OTY1MTI1MzIsIm5iZiI6MTY5NjUxMjIzMiwicGF0aCI6Ii8xMjMzODA3NjAvMjcyOTE3MDIzLWQwNTY3YzVlLWM5YTItNGY2NC05NmU1LTFkODY3NWM3MTM5My5qcGc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBSVdOSllBWDRDU1ZFSDUzQSUyRjIwMjMxMDA1JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDIzMTAwNVQxMzIzNTJaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT00ZTM1MDhkYzEyNWQ3ZjIyMTNkMjdkYzEwMDgxYzA2NDg3NDY1M2VlMzE1ZTVlY2EwYThiNWJmZDFlZjg1MTVkJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.Ta2gzQc97z4ZLI5nIzp9Fja-SvUtaLoW4s9hjxQVg9s

# APPLICATION REQUIREMENTS AND CONFIGURATION SETTINGS

## Postgre SQL Configuration Settings
    -  url: jdbc:postgresql://localhost//ClusterIP:5432/HRMSAuthDB
    username: hrmsuser1
    password: 12345
## MongoDB Configuration Settings 
    - mongodb:
      host: localhost // ClusterIP
      port: 27017
      database: hrmsdb
      username: admin12
      password: hrms**
## Rabbit Configuration Settings
    -rabbitmq:
    host: localhost//ClusterIP
    port: 5672
    username: hrms1
    password: hrms**
    -Terminal codes for add user rabbitmq (Docker containers terminal)
    rabbitmqctl add_user newadmin s0m3p4ssw0rd
    rabbitmqctl set_user_tags newadmin administrator
    rabbitmqctl set_permissions -p / newadmin ".*" ".*" ".*"
