# db.meglab.org (backend)
### Overview

This repository contains the backend (REST API) [Java Dropwizard](https://www.dropwizard.io/en/latest/) source code for the MEGARes database browser that's hosted at https://db.meglab.org. The REST API allows the front-end Javascript code to fetch data from the MEGARes Postgres database. Both the Postgres database and the REST API java server application is hosted on the "Microbial Ecology Group's [fly.io](https://fly.io) account.

### Technology stack

- REST API implemented using Java with [Dropwizard](https://www.dropwizard.io/en/latest/)
- MEGARes data hosted on Postgres 14.4, on the [fly.io PaaS](https://fly.io).
- Continuos integration using [Github Actions](https://github.com/features/actions) ensuring automatic deployment to fly.io
- Docker using [amazoncorretto](https://hub.docker.com/_/amazoncorretto)
### Making changes to the backend code

1. Clone the repository
1. Set up a local instance of Postgres
1. Tailor config.yml to your setup
1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/megares-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`
1. Push to the master branch to initiate GitHub Actions's automatic build and deployment tasks

### Connecting to the Postgres database at fly.io

1.  Download and install [WireGuard](https://www.wireguard.com/install/) (VPN client)
1.  Download and install [PGAdmin](https://www.pgadmin.org/download/)
1.  Download and install [fly.io's command line app](https://fly.io/docs/hands-on/install-flyctl/)
1.  Sign into [fly.io](https://fly.io/docs/hands-on/sign-in/)
1.  Follow the [fly.io instructions](https://fly.io/docs/reference/private-networking/#step-by-step) to create a WireGuard VPN tunnel into fly.io's private network
1.  Add the generated credentials to a ".conf" file that you import into WireGuard (run the WireGuard VPN client)
1.  Connect to the fly.io VPN using the WireGuard VPN client
1.  Open PGadmin and add a new connection with the following details (call the connection "megares-flyio", for example): 
    -  Host: megares-dw-db.internal
    -  Port: 5432
    -  Maintenance database: postgres
    -  Username: postgres

Once connected, you can perform SQL SELECTS and UPDATES according to SQL and Postgres syntax.

### Exporting the database data to a CSV file

The database contains two tables: "descriptions" and "sequences". Each table's data can be exported to a standard UTF-8 based CSV file, that in turn can be opened in a text editor or a spreadsheet editor like Microsoft Excel.

1. Connect to the Postgres database using PGadmin.
1. In the browser pane, drill down from "Servers" - "megares-flyio" (or whatever you named the connection) - "Databases" - "megares_dw" - "Schemas" - "amrdb" - "Tables".
1. Right click on either "descriptions" or "sequences" to bring up the context menu for the table you want to export.
1. Select "Import/Export data.."
1. In the dialog that appears, make sure "Export" is checked, select a path to save the file and specify either "descriptions.csv" or "sequences.csv", depending on which table you are exporting. Make sure the format is "csv" and the Encoding is "UTF8"
1. Under the "Options" tab, make sure "Header" is selected, "Delimiter" is "," and standard double quotes (") are used. Let the Escape character be (').
1. Under the "Columns" table, all the columns should be selected by default ("id", "term", "description" and "links" for the Descriptions table and "id", "header", "fasta", "type", "class", "mechanism" and "seq_group" for the Sequences table)
1. Click "OK" to export. This shouldn't more than a minute (depends on your Internet bandwidth).

### Importing CSV files to the database

To replace data in an existing table you must first delete the existing data:

1. Connect to the Postgres database using PGadmin.
1. Go to "Tools" - "Query tool"
1. Execute either the SQL commands: "DELETE FROM descriptions;" or "DELETE FROM sequences" to wipe the data for the table you want to populate with new data.
1. In the browser pane, drill down from "Servers" - "megares-flyio" (or whatever you named the connection) - "Databases" - "megares_dw" - "Schemas" - "amrdb" - "Tables".
1. Right click on either "descriptions" or "sequences" to bring up the context menu for the table you want to import data for.
1. Select "Import/Export data.."
1. In the dialog that appears, make sure "Import" is checked, select the path to where your CSV file is located.
1. Make sure the settings are identical to the ones you used when you exported data.
1. Click Import to import data from your CSV file. Repeat 

If you are just adding new rows, either use the query tool or import a CSV with rows that don't already exist in the database. In this case, do not delete the existing data.
