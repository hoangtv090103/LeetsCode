import { authModalState } from "@/atoms/authModalAtom";
import React from "react";
import { useSetRecoilState } from "recoil";

type SignupProps = {};

const Signup: React.FC<SignupProps> = () => {
  const setAuthModalState = useSetRecoilState(authModalState);
  const handleClick = (type: "login" | "signup" | "resetPassword") => {
    setAuthModalState((prev) => ({ ...prev, type }));
  };
  return (
    <div>
      <form className="space-y-6 px-6 py-5">
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
            className="border-2 outline-none sm:text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5  border-gray-500 placeholder-gray-400 text-white"
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
            type="password"
            name="password"
            id="password"
            className="border-2 outline-none sm:text-sm rounded-lg focus:ring-blue-500 focus focus:border-blue-500 block w-full p-2.5  border-gray-500 placeholder-gray-400 text-white"
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
