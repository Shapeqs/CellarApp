// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  url:"http://localhost:8080",
  apiUrls:{
    login:'http://localhost:8080/api/login/',
    bottles: 'http://localhost:8080/api/bottles/',
    castels: 'http://localhost:8080/api/castels/',
    namings: 'http://localhost:8080/api/namings/',
    clients: 'http://localhost:8080/api/clients/',
    orders: 'http://localhost:8080/api/orders/',
    images: 'http://localhost:8080/api/images/',
    employes: 'http://localhost:8080/api/employes/',
    update_user: 'http://localhost:8080/api/updateUser/'
  }

};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
