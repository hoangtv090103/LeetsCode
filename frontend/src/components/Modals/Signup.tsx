import { authModalState } from "@/atoms/authModalAtom";
import React, { useState } from "react";
import { useSetRecoilState } from "recoil";
import axios from "axios";
import { useRouter } from "next/router";

axios.defaults.baseURL = "http://localhost:8080/api/v1";

type SignupProps = {};

const Signup: React.FC<SignupProps> = () => {
  const setAuthModalState = useSetRecoilState(authModalState);
  const handleClick = (type: "login" | "signup" | "resetPassword") => {
    setAuthModalState((prev) => ({ ...prev, type }));
  };
  const [inputs, setInputs] = useState({
    email: "",
    displayName: "",
    password: "",
  });
  // const router = useRouter();
  const handleChangeInput = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInputs((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleRegister = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log(e);
    try {
      const response = await axios.post("/users", inputs);
      // Handle the response here
      console.log(response.data);

      // router.push("/");
    } catch (error) {
      // Handle the error here
      console.error(error);
    }
  };

  console.log(inputs);
  return (
    <div>
      <form className="space-y-6 px-6 py-5" onSubmit={handleRegister}>
        <h3 className="text-x; font-medium text-white">
          Register to LeetsCode
        </h3>
        <div>
          <label
            htmlFor="email"
            className="block text-sm font-medium text-white mb-2 text-gray"
          >
            Email
          </label>
          <input
            type="email"
            id="email"
            name="email"
            onChange={handleChangeInput}
            className="border-2 outline-none sm:text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5  border-gray-500 placeholder-gray-400 text-white bg-gray-600"
            placeholder="name@company.com"
          />
        </div>
        <div>
          <label
            htmlFor="displayName"
            className="block text-sm font-medium text-white mb-2 text-gray"
          >
            Display Name
          </label>
          <input
            type="text"
            id="displayName"
            name="displayName"
            onChange={handleChangeInput}
            className="border-2 outline-none sm:text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 bg-gray-600 border-gray-500 placeholder-gray-400 text-white"
            placeholder="John Doe"
          />
        </div>
        <div>
          <label
            htmlFor="password"
            className="text-sm font-medium block mb-2 text-gray-300"
          >
            Password
          </label>
          <input
            onChange={handleChangeInput}
            type="password"
            name="password"
            id="password"
            className="border-2 outline-none sm:text-sm rounded-lg focus:ring-blue-500 focus focus:border-blue-500 block w-full p-2.5  border-gray-500 placeholder-gray-400 text-white bg-gray-600"
            placeholder="********"
          />
        </div>
        <button
          type="submit"
          className="w-full text-white focus:ring-blue-300 font-medium rounder-lg text-sm px-5 py-2.5 text-center bg-brand-orange hover:bg-brand-orange-s"
          onClick={() => handleClick("signup")}
        >
          Register
        </button>

        <div className="text-sm font-medium text-gray-300">
          Alreay have an account?{" "}
          <a
            href="#"
            className="text-blue-700 hover:underline"
            onClick={() => handleClick("login")}
          >
            Login
          </a>
        </div>
      </form>
    </div>
  );
};

export default Signup;
