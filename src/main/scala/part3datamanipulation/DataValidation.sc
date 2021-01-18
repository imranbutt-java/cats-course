import cats.data.Validated

object FormValidation {
  type FormValidation[T] = Validated[List[String], T]

  /*
      fields are
      - name
      - email
      - password

      rules are
      - name, email and password MUST be specified
      - name must not be blank
      - email must have "@"
      - password must have >= 10 characters
     */

  def validatedForm(form: Map[String, String]): FormValidation[String] = ???
}